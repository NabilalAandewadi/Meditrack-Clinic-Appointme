package com.airtribe.meditrack.repository;


public interface Payable {
    double generateBill();

    // Default method
    default double applyDiscount(double amount, double discount) {
        return amount * (1 - discount);
    }
}