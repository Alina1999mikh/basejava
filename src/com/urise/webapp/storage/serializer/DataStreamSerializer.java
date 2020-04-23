package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.util.DataUtil;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());
                Section value = entry.getValue();
                writeSection(dos, type, value);
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private void writeSection(DataOutputStream dos, SectionType type, Section value) throws IOException {
        switch (type) {
            case PERSONAL:

            case OBJECTIVE:
                dos.writeUTF(((TextSection) value).getText());
                break;

            case ACHIEVEMENT:

            case QUALIFICATIONS:
                List<String> collection = ((ListSection) value).getItems();
                writeCollection(dos, collection, dos::writeUTF);
                break;

            case EXPERIENCE:

            case EDUCATION:
                writeCollection(dos, ((OrganizationSection) value).getOrganizations(), organization ->
                {
                    dos.writeUTF(organization.getHomePage().getName());
                    writeNullString(dos, organization.getHomePage().getUrl());
                    writeCollection(dos, organization.getOrganizationPeriod(), period -> {
                        writeLocalDate(dos, period.getStartDate());
                        writeLocalDate(dos, period.getEndDate());
                        dos.writeUTF(period.getTitle());
                        writeNullString(dos, period.getText());
                    });
                });
                break;

            default:
                throw new IllegalStateException();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private String transformNullString(String text) {
        if (text.equals("")) return null;
        else return text;
    }

    private void writeNullString(DataOutputStream dos, String string) throws IOException {
        dos.writeUTF(Objects.requireNonNullElse(string, ""));
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, WriteItem<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    @FunctionalInterface
    private interface WriteItem<T> {
        void write(T t) throws IOException;
    }

    private Section readSection(DataInputStream dis, SectionType type) throws IOException {
        switch (type) {
            case PERSONAL:

            case OBJECTIVE:
                return new TextSection(dis.readUTF());

            case ACHIEVEMENT:

            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));

            case EXPERIENCE:

            case EDUCATION:
                return new OrganizationSection(
                        readList(dis, () -> new Organization(
                                dis.readUTF(), transformNullString(dis.readUTF()),
                                readList(dis, () -> new OrganizationPeriod(
                                        readLocalDate(dis), readLocalDate(dis), dis.readUTF(), transformNullString(dis.readUTF())
                                )))));

            default:
                throw new IllegalStateException();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return DataUtil.of(dis.readInt(), dis.readInt());
    }

    @FunctionalInterface
    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    @FunctionalInterface
    private interface ElementProcessor {
        void process() throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }
}