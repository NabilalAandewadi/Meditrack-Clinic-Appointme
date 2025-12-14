package com.airtribe.meditrack.repository;



import com.airtribe.meditrack.constant.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findBySpecialization(Specialization specialization);
}