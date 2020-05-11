package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final File PROPS = new File("./config/resumes.properties");
    private Properties props = new Properties();

    public static Config get() {
        return INSTANCE;
    }

    private File storageDir;

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid");
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
