package com.airtribe.meditrack.controller;


import com.airtribe.meditrack.constant.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String query) {
        return doctorService.searchDoctors(query);
    }

    @GetMapping("/specialization/{spec}")
    public List<Doctor> searchBySpecialization(@PathVariable Specialization spec) {
        return doctorService.searchDoctorsBySpecialization(spec);
    }
}