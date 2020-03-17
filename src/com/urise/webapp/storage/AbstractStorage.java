package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    protected final Logger log = Logger.getLogger(getClass().getName());
    private static final Logger LOG=Logger.getLogger(AbstractStorage.class.getName());
    @Override
    public void update(Resume resume) {
        LOG.info("Update: "+resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save: "+resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get: "+uuid);
        SK index = getExistedSearchKey(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete: "+uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> listStorage = getAll();
        Collections.sort(listStorage);
        LOG.info("Get all: "+listStorage);
        return listStorage;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume "+ uuid+" is not exist!");
            throw new NotExistStorageException(uuid);
        }
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume "+ uuid+" already exist!");
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(SK index);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract SK findSearchKey(String uuid);

    protected abstract List<Resume> getAll();
}