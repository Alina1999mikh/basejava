package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private Link homePage;
    private final List<OrganizationPeriod> organizationPeriod;

    public Organization(String name, String url, List<OrganizationPeriod> organizationPeriod) {
        Objects.requireNonNull(name, "Name can't be NULL!");
        this.homePage = new Link(name, url);
        this.organizationPeriod = organizationPeriod;
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