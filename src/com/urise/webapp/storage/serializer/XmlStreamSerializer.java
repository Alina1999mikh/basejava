package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.util.XmlParser;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.charset.StandardCharsets;

@XmlRootElement
public class XmlStreamSerializer implements StreamSerializer {

    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class, TextSection.class, ListSection.class, OrganizationPeriod.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshal(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshal(reader);
        }
    }
}