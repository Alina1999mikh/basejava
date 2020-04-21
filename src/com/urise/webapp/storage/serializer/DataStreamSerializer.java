package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contact = resume.getContacts();
            dos.writeInt(contact.size());
            for (Map.Entry<ContactType, String> entry : contact.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> section = resume.getSections();
            dos.writeInt(section.size());
            for (Map.Entry<SectionType, Section> entry : section.entrySet()) {
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());
                Section value = entry.getValue();
                writeSection(dos, type, value);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                resume.addSection(type, readSection(type, dis));
            }
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
                dos.writeInt(collection.size());
                for (String i : collection) {
                    dos.writeUTF(i);
                }
                break;

            case EXPERIENCE:

            case EDUCATION:
                int sizeOrganization = ((OrganizationSection) value).getOrganizations().size();
                dos.writeInt(sizeOrganization);
                for (Organization organization : ((OrganizationSection) value).getOrganizations()) {
                    dos.writeUTF(organization.getHomePage().getName());
                    writeNullString(dos, organization.getHomePage().getUrl());
                    List<OrganizationPeriod> collectionPeriod = organization.getOrganizationPeriod();
                    dos.writeInt(collectionPeriod.size());
                    for (OrganizationPeriod period : collectionPeriod) {
                        writeLocalDate(dos, period.getStartDate());
                        writeLocalDate(dos, period.getEndDate());
                        dos.writeUTF(period.getTitle());
                        writeNullString(dos, period.getText());
                    }
                }
                break;

            default:
                throw new IllegalStateException();
        }
    }

    private Section readSection(SectionType type, DataInputStream dis) throws IOException {
        switch (type) {
            case PERSONAL:

            case OBJECTIVE:
                return new TextSection(dis.readUTF());

            case ACHIEVEMENT:

            case QUALIFICATIONS:
                int size = dis.readInt();
                List<String> list = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    list.add(dis.readUTF());
                }
                return new ListSection(list);

            case EXPERIENCE:

            case EDUCATION:
                int sizeOrganization = dis.readInt();
                List<Organization> listOrg = new ArrayList<>(sizeOrganization);
                for (int i = 0; i < sizeOrganization; i++) {
                    String name = dis.readUTF();
                    String url = transformNullString(dis.readUTF());
                    int sizePeriod = dis.readInt();
                    List<OrganizationPeriod> organizationPeriods = new ArrayList<>(sizePeriod);
                    for (int j = 0; j < sizePeriod; j++) {
                        organizationPeriods.add(new OrganizationPeriod(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt(), dis.readUTF(), transformNullString(dis.readUTF())));
                    }
                    listOrg.add(new Organization(name, url, organizationPeriods));
                }
                return new OrganizationSection(listOrg);

            default:
                throw new IllegalStateException();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private String transformNullString(String text) {

        if (text.equals("null")) return null;
        else return text;
    }

    private void writeNullString(DataOutputStream dos, String string) throws IOException {
        dos.writeUTF(Objects.requireNonNullElse(string, "null"));
    }
}