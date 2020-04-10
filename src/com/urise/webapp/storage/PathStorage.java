package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private Strategy strategy;

    PathStorage(String dir, Strategy strategy) {
        Objects.requireNonNull(dir, "directory can't be null");
        directory = Paths.get(dir);
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
        return Files.isRegularFile(file);
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
    protected Resume doGet(Path file) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Get error", file.getFileName().toString(), e);
        }
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
    protected List<Resume> getAll() {
        return getStreamPath().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getStreamPath().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getStreamPath().count();
    }

    private Stream<Path> getStreamPath() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Get stream path error", directory.getFileName().toString(), e);
        }
    }
}