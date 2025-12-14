package com.airtribe.meditrack.entity;



import com.airtribe.meditrack.constant.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;

import com.airtribe.meditrack.repository.Searchable;
import com.airtribe.meditrack.util.Validator;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Doctor extends Person implements Searchable,Cloneable {
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private double fee;

    public Doctor() {}

    public Doctor(String name, int age, String contact, Specialization specialization, double fee) {
        super(name, age, contact);
        this.specialization = specialization;
        this.fee = fee;
    }

    @Override
    public void validate() throws InvalidDataException {
        super.validate();
        Validator.validateFee(fee);
    }

    @Override
    public Doctor clone() throws CloneNotSupportedException {
        return (Doctor) super.clone();  // Shallow copy is fine since fields are primitives/strings
    }
    @Override
    public boolean matches(String query) {
        return getName().contains(query) || specialization.name().contains(query);
    }

    // Getters/Setters
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String getType() {
        return "Doctor";
    }
}