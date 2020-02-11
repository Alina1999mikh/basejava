package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object index = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void save(Resume resume) {
        Object index = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getExistedSearchKey(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getExistedSearchKey(uuid);
        doDelete(index);
    }

    private Object getExistedSearchKey(String uuid) {
        Object index = findSearchKey(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object index = (uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object index);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object findSearchKey(String uuid);
}
