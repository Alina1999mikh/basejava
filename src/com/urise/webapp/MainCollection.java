package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

public class MainCollection {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public static void main(String[] args) {
        ListStorage listStorage = new ListStorage();
        listStorage.save(RESUME_1);
        listStorage.save(RESUME_2);
        listStorage.save(RESUME_3);
        listStorage.printAll();
        listStorage.update(RESUME_2);
        System.out.println(listStorage.size());
        System.out.println("get" + listStorage.get("uuid1"));
        listStorage.clear();
        System.out.println(listStorage.size());

    }
}
