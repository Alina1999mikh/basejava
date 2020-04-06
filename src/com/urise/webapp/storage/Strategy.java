package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {

    public void doWrite(Resume resume, OutputStream file) throws IOException;

    public Resume doRead(InputStream file) throws IOException;
}