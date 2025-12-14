package com.airtribe.meditrack.controller;



import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/{doctorId}/{patientId}")
    public Appointment createAppointment(@PathVariable String doctorId, @PathVariable String patientId, @RequestBody Appointment appointment) {
        return appointmentService.createAppointment(doctorId, patientId, appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PutMapping("/cancel/{id}")
    public Appointment cancelAppointment(@PathVariable String id) {
        return appointmentService.cancelAppointment(id);
    }

    @PostMapping("/bill/{appointmentId}")
    public Bill generateBill(@PathVariable String appointmentId, @RequestParam double amount) {
        return appointmentService.generateBill(appointmentId, amount);
    }

    @GetMapping("/export-csv")
    public ResponseEntity<String> exportToCSV() {
        appointmentService.exportToCSV();
        return ResponseEntity.ok("Exported to CSV");
    }
}
