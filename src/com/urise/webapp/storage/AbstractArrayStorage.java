package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else System.out.println("\nResume is not present");
    }

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            int index=getIndex(resume.getUuid()); // TODO WHAT ABOUT RETURN
            System.out.println(index);
            if (index < 0) {
                insertElement(resume, index);
                size++;
            } else {
                System.out.println("\nResume is present");
            }

        } else {
            System.out.println("\nStorage is overflow");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            if (index != size - 1) {
                fillDeletedElement(index);
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume is not present");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume is not present");
            return null;
        }
    }

    protected abstract int getIndex(String uuid);
    protected abstract void insertElement(Resume resume, int index);
    protected abstract void fillDeletedElement(int index);
}