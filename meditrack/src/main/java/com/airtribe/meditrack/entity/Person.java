package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends MedicalEntity {
    private String name;
    private int age;
    private String contact;

    public Person() {}

    public Person(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    @Override
    public void validate() throws InvalidDataException {
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validateContact(contact);
    }

    // Encapsulation: getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Polymorphism: override in subclasses
    public String getType() {
        return "Person";
    }
}
