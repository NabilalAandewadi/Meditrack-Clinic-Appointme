package com.airtribe.meditrack.service;


import com.airtribe.meditrack.constant.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.repository.DoctorRepository;
import com.airtribe.meditrack.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor doctor) {
        try {
            doctor.validate();
            doctor.setId(IdGenerator.getInstance().generateId("DOC-"));
            return doctorRepository.save(doctor);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid doctor data", e);
        }
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Streams + Lambdas
    public List<Doctor> searchDoctorsBySpecialization(Specialization spec) {
        return doctorRepository.findBySpecialization(spec);
    }

    public List<Doctor> searchDoctors(String query) {
        return getAllDoctors().stream()
                .filter(doctor -> doctor.matches(query))
                .collect(Collectors.toList());
    }

    // Polymorphism: overload
    public Doctor findById(String id) {
        return doctorRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Doctor not found"));
    }
}
