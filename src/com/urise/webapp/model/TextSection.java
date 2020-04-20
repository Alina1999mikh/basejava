package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class TextSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "Text can't be NULL!");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TextSection() {
    }

    @Override
    public String toString() {
        return (text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}