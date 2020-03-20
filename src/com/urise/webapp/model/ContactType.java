package com.urise.webapp.model;

public enum ContactType {
    PHONE ("Тел.:"),
    SKYPE("Skype:"),
    MAIL(""),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME("Домашняя страница:");

    private String title;

    ContactType(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
}
