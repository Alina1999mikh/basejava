package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private File directory;

    private Strategy strategy;

    FileStorage(File directory, Strategy strategy) {
        Objects.requireNonNull(directory, "directory can't be null");
        this.strategy = strategy;
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
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Update error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (!file.createNewFile()) throw new StorageException("File cannot be create", file.getName());
        } catch (IOException e) {
            throw new StorageException("Save error", file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Get error", file.getName(), e);
        }
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
        File[] list = getFiles();
        for (File file : list) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] list = getFiles();
        for (File file : list) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] list = getFiles();
        return list.length;
    }

    private File[] getFiles() {
        File[] list = directory.listFiles();
        if (list != null) {
            return list;
        } else {
            throw new StorageException("Directory is null!", directory.getName());
        }
    }
}