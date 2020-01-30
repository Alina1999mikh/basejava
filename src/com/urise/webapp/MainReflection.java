package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) {
        Resume r=new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());
        Field field2 = r.getClass().getDeclaredFields()[1];
        System.out.println(field2.getName());
    }
}
