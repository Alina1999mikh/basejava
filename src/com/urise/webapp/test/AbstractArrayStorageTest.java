package com.urise.webapp.test;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    AbstractArrayStorageTest(Storage storage) {
        this.storage=storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(RESUME_1.getUuid());
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));

        newResume = new Resume(RESUME_2.getUuid());
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_2));

        newResume = new Resume(RESUME_3.getUuid());
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_3));
    }


    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        final Resume newResume = new Resume("isNotExist");
        storage.update(newResume);
    }

    @Test
    public void save() {
        final String UUID_1 = "newUuid_1";
        final Resume RESUME_1 = new Resume(UUID_1);
        storage.save(RESUME_1);
        final String UUID_2 = "newUuid_2";
        final Resume RESUME_2 = new Resume(UUID_2);
        storage.save(RESUME_2);
        final String UUID_3 = "newUuid_3";
        final Resume RESUME_3 = new Resume(UUID_3);
        storage.save(RESUME_3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveIsExist() {
        final String UUID_EXIST = "uuid1";
        final Resume RESUME_EXIST = new Resume(UUID_EXIST);
        storage.save(RESUME_EXIST);
    }

    @Test(expected = StorageException.class)
    public void saveIsOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("itIsNotExist");
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        assertSize(3);
        Assert.assertEquals(RESUME_1, all[0]);
        Assert.assertEquals(RESUME_2, all[1]);
        Assert.assertEquals(RESUME_3, all[2]);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
        Assert.assertEquals(RESUME_3, storage.get(RESUME_3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("itIsNotExist");
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

}