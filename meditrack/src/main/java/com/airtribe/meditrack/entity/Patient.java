package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.exception.InvalidDataException;

import com.airtribe.meditrack.repository.Searchable;
import jakarta.persistence.Entity;

@Entity
public class Patient extends Person implements Searchable, Cloneable {
    private String symptoms;

    public Patient() {}

    public Patient(String name, int age, String contact, String symptoms) {
        super(name, age, contact);
        this.symptoms = symptoms;
    }

    @Override
    public void validate() throws InvalidDataException {
        super.validate();
        // Additional validation if needed
    }

    @Override
    public boolean matches(String query) {
        return getName().contains(query) || String.valueOf(getAge()).equals(query);
    }

    // Deep copy cloning
    @Override
    public Patient clone() throws CloneNotSupportedException {
        Patient cloned = (Patient) super.clone();
        cloned.symptoms = this.symptoms; // Primitive/string, shallow ok, but demo deep
        return cloned;
    }

    // Getters/Setters
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String getType() {
        return "Patient";
    }
}
