package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("name");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        field.get(r);
        System.out.println(r);
        Method method = r.getClass().getMethod("toString");
        String d = (String) method.invoke(r);
        System.out.println(d);
    }
}
