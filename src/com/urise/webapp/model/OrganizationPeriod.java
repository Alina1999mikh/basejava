package com.urise.webapp.model;

import com.urise.webapp.storage.util.DataUtil;
import com.urise.webapp.storage.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationPeriod implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String text;

    public OrganizationPeriod(int startDateYear, int startDateMonth, int endDateYear, int endDateMonth, String title, String text) {
        Objects.requireNonNull(title, "Title can't be NULL!");
        this.startDate = DataUtil.of(startDateYear, startDateMonth);
        this.endDate = DataUtil.of(endDateYear,endDateMonth);
        this.title = title;
        this.text = text;
    }

    public OrganizationPeriod(int startDateYear, int startDateMonth, LocalDate endDateNow, String title, String text) {
        Objects.requireNonNull(title, "Title can't be NULL!");
        this.startDate = DataUtil.of(startDateYear,startDateMonth);
        Objects.requireNonNull(endDateNow, "End date can't be NULL!");
        this.endDate = DataUtil.of(endDateNow.getYear(), endDateNow.getMonthValue());
        this.title = title;
        this.text = text;
    }

    public OrganizationPeriod() {
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