package com.urise.webapp;

import com.urise.webapp.exception.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        for (String name : Objects.requireNonNull(list)) {
            System.out.println(name);
        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File rootDir = new File("C:\\Users\\flenn\\basejava");
        File[] listRoot = rootDir.listFiles();
        assert listRoot != null;
        recursionRound(rootDir, "");

    }

    private static void recursionRound(File file, String indentation) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File name : list) {
                if (name.isDirectory()) {
                    System.out.println(indentation + "Directory: " + name.getName());
                    recursionRound(name, indentation + " ");
                } else {
                    System.out.println(indentation + "File: " + name.getName());
                }
            }
        } else {
            throw new StorageException("List is null!", file.getName());
        }
    }
}