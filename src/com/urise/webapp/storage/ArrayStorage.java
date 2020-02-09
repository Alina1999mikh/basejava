package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return  -1;
    }

    @Override
    protected void insertElement(Resume resume, Object index) {
        storage[size] = resume;
    }

    @Override
    protected void fillDeletedElement(Object index) {
        storage[(Integer) index] = storage[size - 1];
    }
}
