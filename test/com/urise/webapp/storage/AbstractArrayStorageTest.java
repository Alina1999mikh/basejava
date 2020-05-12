package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

abstract public class AbstractArrayStorageTest extends AbstractStorageTest {

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveIsOverflow() throws IOException, SQLException {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(Integer.toString(i)));
            }
        } catch (StorageException | IOException e) {
            Assert.fail(e.getMessage());
        }
        storage.save(new Resume("overflow)"));
    }
}