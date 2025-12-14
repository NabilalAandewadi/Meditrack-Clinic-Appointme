package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.constant.ConstantsDetails;

import com.airtribe.meditrack.exception.InvalidDataException;

import com.airtribe.meditrack.repository.Payable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Bill extends MedicalEntity implements Payable {
    @OneToOne
    private Appointment appointment;
    private double amount;
    private double tax;
    private double total;

    public Bill() {}

    public Bill(Appointment appointment, double amount) {
        this.appointment = appointment;
        this.amount = amount;
        this.tax = amount * ConstantsDetails.TAX_RATE;
        this.total = amount + tax;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (appointment == null || amount <= 0) {
            throw new InvalidDataException("Invalid bill data");
        }
    }

    @Override
    public double generateBill() {
        return total;
    }

    // Getters/Setters
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
