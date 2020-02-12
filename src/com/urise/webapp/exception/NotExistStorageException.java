package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("\nResume " + uuid + " is not exist", uuid+"\n");
    }
}
