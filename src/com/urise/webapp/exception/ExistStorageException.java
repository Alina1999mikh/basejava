package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("\nResume " + uuid + " is  exist", uuid+"\n");
    }
}