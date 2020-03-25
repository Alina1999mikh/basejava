package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        recursionRound(Objects.requireNonNull(listRoot));

    }

    private static void recursionRound(File[] listRoot) {

        for (File file : listRoot) {
            System.out.println(file);
            if (file.isDirectory()) {
                recursionRound(Objects.requireNonNull(file.listFiles()));
            }
        }
    }
}