package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume resume, Object index) {
        storage[size] = resume;
    }

    @Override
    protected void fillDeletedElement(Object index) {
        storage[(Integer) index] = storage[size - 1];
    }

    @Override
    protected List<Resume> getAllSorted() {
        List<Resume> listStorage;
        listStorage = Arrays.asList((Arrays.copyOfRange(storage, 0, size)));
        listStorage.sort(Comparator.comparing(Resume::getUuid));
        return listStorage;
    }
}