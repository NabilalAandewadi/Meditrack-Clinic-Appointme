package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.exception.InvalidDataException;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MedicalEntity {
    @Id
    private String id; // Generated via IdGenerator

    // Common behavior
    public abstract void validate() throws InvalidDataException;

    // Getters/Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
