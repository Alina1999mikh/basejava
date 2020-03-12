package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        listStorage.set((Integer) index, resume);
    }

    @Override
    public void doSave(Resume resume, Object index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(Object index) {
        return listStorage.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        listStorage.remove(((Integer) index).intValue());
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> listStorageSort = new ArrayList<>(listStorage);
        Collections.sort(listStorageSort);
        return listStorageSort;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public Integer findSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }
}