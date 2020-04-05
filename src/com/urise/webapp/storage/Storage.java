package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume) throws IOException;

    Resume get(String uuid) throws IOException;

    void delete(String uuid) throws IOException;

    int size() throws IOException;

    List<Resume> getAllSorted() throws IOException;
}