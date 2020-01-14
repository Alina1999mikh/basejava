package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);

    }

    @Override  //TODO REALISATION
    protected void insertElement(Resume resume, int index) {
        System.out.println("index= "+ index);

        if(index<0) index=index*(-1)-1;
        if(index>size)  storage[size] = resume;
        else{ System.arraycopy(storage, index, storage, index + 1, size-index);
        storage[index]=resume;}
    }

    @Override
    protected void fillDeletedElement(int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
    }


}
