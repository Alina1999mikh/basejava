package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<OrganizationPeriod> organizationPeriod;


    public Organization(String name, String url, List<OrganizationPeriod> organizationPeriod) {
        this.homePage = new Link(name, url);
        this.organizationPeriod = organizationPeriod;
    }

    public Organization() {
    }

    public List<OrganizationPeriod> getOrganizationPeriod() {
        return organizationPeriod;
    }

    public Link getHomePage() {
        return homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                organizationPeriod.equals(that.organizationPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, organizationPeriod);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", organizationPeriod=" + organizationPeriod +
                '}';
    }
}