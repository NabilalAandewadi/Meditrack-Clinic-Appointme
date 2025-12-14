package com.airtribe.meditrack.util;



import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {
    public static void validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty");
        }
    }

    public static void validateAge(int age) throws InvalidDataException {
        if (age <= 0) {
            throw new InvalidDataException("Age must be positive");
        }
    }

    public static void validateContact(String contact) throws InvalidDataException {
        if (contact == null || !contact.matches("\\d{10}")) {
            throw new InvalidDataException("Invalid contact number");
        }
    }

    public static void validateFee(double fee) throws InvalidDataException {
        if (fee <= 0) {
            throw new InvalidDataException("Fee must be positive");
        }
    }
}
