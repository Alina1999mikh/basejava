package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {
    static final File STORAGE_DIR = new File("C:\\Users\\flenn\\basejava\\src\\com\\urise\\webapp\\storageResume");

    Storage storage;

    private static final Resume RESUME_1 = ResumeTestData.RESUME_1;
    private static final Resume RESUME_2 = ResumeTestData.RESUME_2;
    private static final Resume RESUME_3 = ResumeTestData.RESUME_3;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws IOException {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() throws IOException {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws IOException {
        Resume newResume = new Resume(RESUME_1.getUuid(), "testName");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        final Resume newResume = new Resume("isNotExist", "testName");
        storage.update(newResume);
    }

    @Test
    public void save() throws IOException {
        final String newUuid = "newUuid";
        final Resume resume = new Resume(newUuid, "testName");
        storage.save(resume);
        assertGet(resume);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveIsExist() throws IOException {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws IOException {
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws IOException {
        storage.delete("itIsNotExist");
    }

    @Test
    public void getAllSorted() throws IOException {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        List<Resume> listComparison = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(listComparison);
        Assert.assertEquals(list, listComparison);
    }

    @Test
    public void size() throws IOException {
        assertSize(3);
    }

    @Test
    public void get() throws IOException {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws IOException {
        storage.get("itIsNotExist");
    }

    private void assertSize(int size) throws IOException {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) throws IOException {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}