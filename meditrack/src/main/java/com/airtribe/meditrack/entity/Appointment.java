package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.constant.AppointmentStatus;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DateUtil;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Appointment extends MedicalEntity implements Cloneable {
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private Date date;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;

    public Appointment() {}

    public Appointment(Doctor doctor, Patient patient, Date date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (doctor == null || patient == null || date == null) {
            throw new InvalidDataException("Invalid appointment data");
        }
        DateUtil.validateDate(date);
    }

    // Deep copy cloning
    @Override
    public Appointment clone() throws CloneNotSupportedException {
        Appointment cloned = (Appointment) super.clone();
 cloned.doctor = this.doctor.clone(); // Assuming Doctor implements Cloneable if needed, but for demo
        cloned.patient = this.patient.clone();
        cloned.date = (Date) this.date.clone();
        return cloned;
    }

    // Getters/Setters
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}