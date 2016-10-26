package ru.abusabir.webapp.model;

import java.util.*;

/**
 * Oleg
 * 30.09.2016
 */

public class Resume { //implements Comparable<Resume> {
    private final String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private List<Section> sections = new LinkedList<>();

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Resume other = (Resume) o;

        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

//    @Override
//    public int compareTo(Resume o) {
//        return fullName.compareTo(o.fullName);
//    }
}
