package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory can't be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }

        if (!directory.canRead() || (!directory.canWrite())) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "directory nor readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Update error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (!file.createNewFile()) throw new StorageException("File cannot be create", file.getName());
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Save error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) throw new StorageException("File cannot be deleted", file.getName());
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            resumes.add(doRead(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        for (File file : Objects.requireNonNull(list)) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }

    abstract void doWrite(Resume resume, File file) throws IOException;

    abstract Resume doRead(File file);
}