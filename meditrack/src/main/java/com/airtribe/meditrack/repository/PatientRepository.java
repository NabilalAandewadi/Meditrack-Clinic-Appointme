package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}