package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        listStorage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        listStorage.add(resume);
    }

    @Override
    public Resume doGet(Integer index) {
        return listStorage.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        listStorage.remove((index).intValue());
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(listStorage);
    }

    @Override
    protected boolean isExist(Integer index) {
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