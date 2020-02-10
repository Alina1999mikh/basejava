package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object index = getIndex(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void save(Resume resume) {
        Object index = getNotIndex(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        doDelete(index);
    }

    private Object getIndex(String uuid) {
        Object index = findIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotIndex(String uuid) {
        Object index = findIndex(uuid);
        if (!isExist(index)) {
            if (index == null) return -1;
            else return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract Object findIndex(String uuid);
}
