package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage {
    private static List<Resume> listStorage = new ArrayList<>();

    public void clear() {
        listStorage.clear();
    }

    public void update(Resume resume) {
        int indexResume = getExist(resume.getUuid());
        listStorage.set(indexResume, resume);
    }

    public void save(Resume resume) {
        if (isNotExist(resume.getUuid())) {
            listStorage.add(resume);
        }
    }

    public void delete(String uuid) {
        int indexResume = getExist(uuid);
        listStorage.remove(indexResume);
    }

    public Resume get(String uuid) {
        return listStorage.get(getExist(uuid));
    }

    public void printAll() {
        listStorage.forEach(System.out::println);
    }

    public int size() {
        return listStorage.size();
    }

    private int getExist(String uuid) {
        for (Resume resume : listStorage) {
            if (resume.getUuid().equals(uuid)) return listStorage.indexOf(resume);
        }
        throw new NotExistStorageException(uuid);
    }

    private boolean isNotExist(String uuid) {
        for (Resume resume : listStorage) {
            if (resume.getUuid().equals(uuid)) throw new ExistStorageException(uuid);
        }
        return true;
    }
}


