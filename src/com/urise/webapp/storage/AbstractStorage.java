package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void update(Resume resume) {
        int index = getExist(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void save(Resume resume) {
        int index = getNotExist(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getExist(uuid);
        return doGet(uuid, index);
    }

    @Override
    public void delete(String uuid) {
        int index = getExist(uuid);
        doDelete(uuid, index);
    }

    @Override
    public int size() {
        return doSize();
    }

    private int getExist(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private int getNotExist(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    private boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract void doClear();

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract int doSize();

    protected abstract int getIndex(String uuid);
}
