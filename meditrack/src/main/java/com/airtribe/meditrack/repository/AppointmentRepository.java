package com.airtribe.meditrack.repository;


import com.airtribe.meditrack.constant.AppointmentStatus;
import com.airtribe.meditrack.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByStatus(AppointmentStatus status);
}
