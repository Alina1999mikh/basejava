package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    private final List<Organization> organization;

    public OrganizationSection(List<Organization> organization) {
        Objects.requireNonNull(organization, "Organization can't be NULL!");
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organization.equals(that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization);
    }
}