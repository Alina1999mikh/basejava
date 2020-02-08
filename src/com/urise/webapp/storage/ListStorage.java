package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends ArrayStorage {
    private static List<Resume> listStorage = new ArrayList<>();

    public void doClear() {
        listStorage.clear();
    }

    public void doUpdate(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    public void doSave(Resume resume, int index) {
        listStorage.add(resume);
    }

    public Resume doGet(String uuid, int index) {
        return listStorage.get(index);
    }

    public void doDelete(String uuid, int index) {
        listStorage.remove(index);
    }

    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    public int doSize() {
        return listStorage.size();
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}


