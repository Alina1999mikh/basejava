package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
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
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
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
    protected Resume doGet(File file) throws IOException {
        return doRead(new BufferedInputStream(new FileInputStream(file)));
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
    protected List<Resume> getAll() throws IOException {
        List<Resume> resumes = new ArrayList<>();
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                resumes.add(doGet(file));
            }
        } else {
            throw new StorageException("Directory is null!", directory.getName());
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                doDelete(file);
            }
        } else {
            throw new StorageException("Directory is null!", directory.getName());
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list != null) {
            return list.length;
        } else {
            throw new StorageException("Directory is null!", directory.getName());
        }
    }

    abstract void doWrite(Resume resume, OutputStream file) throws IOException;

    abstract Resume doRead(InputStream file) throws IOException;
}