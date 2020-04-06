package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private Strategy strategy;

    PathStorage(String dir, Strategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory can't be null");
        this.strategy = strategy;
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }

        if (!Files.isReadable(directory) || (!Files.isWritable(directory))) {
            throw new IllegalArgumentException(dir + "directory nor readable/writable");
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected void doUpdate(Resume resume, Path file) {
        try {
            strategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Update error", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("Save error", file.getFileName().toString(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(Path file) throws IOException {
        return strategy.doRead(new BufferedInputStream(Files.newInputStream(file)));
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("Delete error!", file.getFileName().toString(), e);
        }
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }


    @Override
    protected List<Resume> getAll() throws IOException {
        List<Resume> resumes = new ArrayList<>();
        Files.list(directory).forEach(path -> {
            try {
                resumes.add(doGet(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resumes;
    }

    @Override
    public void clear() { //
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() throws IOException {
        return (int) Files.list(directory).count();
    }
}
