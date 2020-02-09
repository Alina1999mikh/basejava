package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Integer index = getIndex(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void save(Resume resume) {
        int index = getNotIndex(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        doDelete(index);
    }

    private Integer getIndex(String uuid) {
        Integer index = findIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private int getNotIndex(String uuid) {
        Integer index = findIndex(uuid);
        if (!isExist(index)) {
            if (index == null) return -1;
            else return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    private boolean isExist(Integer index) {
        return (index != null) && (index >= 0);
    }

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(int index);

    protected abstract void doDelete(int index);

    protected abstract Integer findIndex(String uuid);
}
