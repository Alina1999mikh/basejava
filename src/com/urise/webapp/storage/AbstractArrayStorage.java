package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{

    static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int exist = getElementPosition(uuid);
        if (exist != -1) {
            return storage[exist];
        }
        else {
            System.out.println("Resume is not present");
            return null;
        }
    }

    protected abstract int getElementPosition(String uuid);
}