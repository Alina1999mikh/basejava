package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        int exist = getExist(resume.getUuid());
        if (exist != -1) {
            storage[exist] = resume;
        } else System.out.println("\nResume is not present");
    }

    public void save(Resume resume) {
        if (size < 10_000) {
            if (getExist(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else System.out.println("\nResume is present");
        } else System.out.println("\nBase is full");
    }


    public Resume get(String uuid) {
        int exist = getExist(uuid);
        if (getExist(uuid) != -1)
            return storage[exist];
        else {
            System.out.println("Resume is not present");
            return null;
        }
    }

    public void delete(String uuid) {
        int exist = getExist(uuid);
        if (exist != -1) {
            if (exist != size - 1) {
                storage[exist] = storage[size - 1];
                storage[size - 1] = null;
            } else storage[exist] = null;
            size--;
        } else System.out.println("Resume is not present");

    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    private int getExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
