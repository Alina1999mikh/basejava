package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamStorage;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()));
    }
}