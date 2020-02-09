package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(int index) {
        return listStorage.get(index);
    }

    @Override
    public void doDelete(int index) {
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}


