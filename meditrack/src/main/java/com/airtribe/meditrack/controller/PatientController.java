package com.airtribe.meditrack.controller;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Polymorphism demo via overload
    @GetMapping("/search/name/{name}")
    public List<Patient> searchByName(@PathVariable String name) {
        return patientService.searchPatients(name);
    }

    @GetMapping("/search/age/{age}")
    public List<Patient> searchByAge(@PathVariable int age) {
        return patientService.searchPatients(age);
    }
}
