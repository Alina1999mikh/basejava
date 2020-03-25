package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
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

    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if(!file.createNewFile()) throw new StorageException("File cannot be create", file.getName());
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected Resume doGet(File file) {
        return null;
    }

    @Override
    protected void doDelete(File file) {

    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected List<Resume> getAll() {
        return null;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        for (File name : Objects.requireNonNull(list)) {
            if (!name.delete()) throw new StorageException("File cannot be deleted", name.getName());
        }
    }

    @Override
    public int size() {
        return 0;
    }
}