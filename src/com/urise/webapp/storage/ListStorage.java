package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage {
    private static ArrayList<Resume> listStorage = new ArrayList<>();

    public void clear() {
        listStorage.clear();
    }

    public void save(Resume resume) {
        listStorage.add(resume);
    }

    public void printAll() {
        listStorage.forEach(System.out::println);
    }

    public int size() {
        return listStorage.size();
    }
}


