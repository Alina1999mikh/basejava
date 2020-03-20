package com.urise.webapp.model;

import java.util.Objects;

public enum SectionType {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    /**
     * gkislin
     * 14.07.2016
     */
}