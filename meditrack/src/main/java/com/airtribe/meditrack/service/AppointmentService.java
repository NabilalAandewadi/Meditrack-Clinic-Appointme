package com.airtribe.meditrack.service;


import com.airtribe.meditrack.constant.AppointmentStatus;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.repository.AppointmentRepository;
import com.airtribe.meditrack.repository.BillRepository;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    public Appointment createAppointment(String doctorId, String patientId, Appointment appointment) {
        try {
            appointment.setDoctor(doctorService.findById(doctorId));
            appointment.setPatient(patientService.findById(patientId));
            appointment.validate();
            appointment.setId(IdGenerator.getInstance().generateId("APP-"));
            return appointmentRepository.save(appointment);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid appointment data", e);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment cancelAppointment(String id) {
        Appointment app = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
        app.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(app);
    }

    // Factory pattern for bill
    public Bill generateBill(String appointmentId, double amount) {
        Appointment app = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
        Bill bill = new Bill(app, amount); // Simple factory
        bill.setId(IdGenerator.getInstance().generateId("BILL-"));
        return billRepository.save(bill);
    }

    // Streams for analytics
    public long getAppointmentsPerDoctor(String doctorId) {
        return getAllAppointments().stream()
                .filter(app -> app.getDoctor().getId().equals(doctorId))
                .count();
    }

    // Bonus: Export to CSV
    public void exportToCSV() {
        CSVUtil.exportAppointmentsToCSV(getAllAppointments());
    }
}
