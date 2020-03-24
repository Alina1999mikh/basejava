package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class OrganizationPeriod {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public OrganizationPeriod(LocalDate startDate, LocalDate endDate, String title, String text) {
        Objects.requireNonNull(startDate, "Start date can't be NULL!");
        Objects.requireNonNull(endDate, "End date can't be NULL!");
        Objects.requireNonNull(title, "Title can't be NULL!");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationPeriod that = (OrganizationPeriod) o;
        return startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                title.equals(that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, text);
    }

    @Override
    public String toString() {
        String string = "OrganizationPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'';
        if (this.text != null) {
            string = string + ", text='" + text + '\'';
        }
        string = string + '}';
        return string;
    }
}