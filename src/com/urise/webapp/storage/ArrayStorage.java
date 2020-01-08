package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Array based storage for Resumes
 */
class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    private int isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1; // THE RESUME IS NOT PRESENT
    }

    void update(String uuid) throws IOException {
        if (uuid != null) {
            int exist = isExist(uuid);
            if (exist != -1) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.println("Input update uuid");
                    String updateUuid = reader.readLine();
                    if (isExist(updateUuid) == -1) {
                        storage[exist].setUuid(updateUuid);
                        break;
                    } else System.out.println("Resume is present");
                }
            } else System.out.println("\nResume is not present");
        }
    }

    void save(Resume r) {
        if (r.getUuid() != null) {
            if (isExist(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else System.out.println("\nResume is present");
        }
    }

    Resume get(String uuid) {
        int exist = isExist(uuid);
        if (isExist(uuid) != -1)
            return storage[exist];
        else return null;
    }

    void delete(String uuid) {
        int exist = isExist(uuid);
        if (exist != -1) {
            if (exist != size - 1) {
                storage[exist] = storage[size - 1];
                storage[size - 1] = null;
            } else storage[exist] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }
}
