package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.repository.PatientRepository;
import com.airtribe.meditrack.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        try {
            patient.validate();
            patient.setId(IdGenerator.getInstance().generateId("PAT-"));
            return patientRepository.save(patient);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid patient data", e);
        }
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Overloading polymorphism
    public List<Patient> searchPatients(String name) {
        return getAllPatients().stream()
                .filter(p -> p.getName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Patient> searchPatients(int age) {
        return getAllPatients().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    public Patient findById(String id) {
        return patientRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Patient not found"));
    }
}