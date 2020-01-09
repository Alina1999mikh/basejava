package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends  AbstractArrayStorage{

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int exist = getElementPosition(resume.getUuid());
        if (exist != -1) {
            storage[exist] = resume;
        } else System.out.println("\nResume is not present");
    }

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (getElementPosition(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("\nResume is present");
            }
        } else {
            System.out.println("\nStorage is overflow");
        }
    }

    public void delete(String uuid) {
        int exist = getElementPosition(uuid);
        if (exist != -1) {
            if (exist != size - 1) {
                storage[exist] = storage[size - 1];
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

    protected int getElementPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
