package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, Object index) {
        int positiveIndex = -(Integer) index - 1;
        System.arraycopy(storage, positiveIndex, storage, positiveIndex + 1, size - positiveIndex);
        storage[positiveIndex] = resume;
    }

    @Override
    protected void fillDeletedElement(Object index) {
        if (size - 1 - (Integer) index >= 0) {
            System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - 1 - (Integer) index);
        }
    }
}
