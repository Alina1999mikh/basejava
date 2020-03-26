package com.urise.webapp.model;

import java.util.Objects;

public class Link {
    private final String name;
    private final String url;

    Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return name.equals(link.name) &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        String string;
        if (this.url != null) {
            string = "Link{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        } else {
            string = "name='" + name + '\'';
        }
        return string;
    }
}