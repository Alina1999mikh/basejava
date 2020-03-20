package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private Link homePage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String text) {
        Objects.requireNonNull(startDate, "Start date can't be NULL!");
        Objects.requireNonNull(endDate, "End date can't be NULL!");
        Objects.requireNonNull(title, "Title can't be NULL!");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                title.equals(that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, title, text);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}