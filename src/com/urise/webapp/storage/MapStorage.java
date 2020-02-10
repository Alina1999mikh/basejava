package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return map.get((String) index);
    }

    @Override
    protected void doDelete(Object index) {
        map.remove((String) index);
    }

    @Override
    public int size() {
        return map.size();
    }

    public List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected Object findIndex(String uuid) {
        if (map.containsKey(uuid))
            return uuid;
        else return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}
