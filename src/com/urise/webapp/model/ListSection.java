package com.urise.webapp.model;

import com.urise.webapp.storage.ListStorage;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section{
    private final List<String> items;

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "List can't be NULL!");
        this.items = items;
    }

    public List<String> getItems(){
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}