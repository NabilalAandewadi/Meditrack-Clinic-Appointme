package com.airtribe.meditrack;


import com.airtribe.meditrack.constant.Specialization;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeditrackApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders getHeaders(String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(username, password);
		return headers;
	}

	@Test
	void testCreateAndGetDoctor() {
		Doctor doctor = new Doctor("Dr. Smith", 45, "1234567890", Specialization.GENERAL, 100.0);
		HttpEntity<Doctor> request = new HttpEntity<>(doctor, getHeaders("admin", "admin"));

		ResponseEntity<Doctor> response = restTemplate.postForEntity("/api/doctors", request, Doctor.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("Dr. Smith");

		ResponseEntity<Doctor[]> getResponse = restTemplate.exchange("/api/doctors", HttpMethod.GET, new HttpEntity<>(getHeaders("user", "user")), Doctor[].class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponse.getBody().length).isGreaterThan(0);
	}

	@Test
	void testCreateAppointment() {
		// First create doctor and patient
		Doctor doctor = new Doctor("Dr. Test", 50, "0987654321", Specialization.CARDIOLOGY, 200.0);
		Patient patient = new Patient("Patient Test", 30, "1122334455", "Headache");

		HttpEntity<Doctor> docRequest = new HttpEntity<>(doctor, getHeaders("admin", "admin"));
		ResponseEntity<Doctor> docResponse = restTemplate.postForEntity("/api/doctors", docRequest, Doctor.class);
		String doctorId = docResponse.getBody().getId();

		HttpEntity<Patient> patRequest = new HttpEntity<>(patient, getHeaders("admin", "admin"));
		ResponseEntity<Patient> patResponse = restTemplate.postForEntity("/api/patients", patRequest, Patient.class);
		String patientId = patResponse.getBody().getId();

		Appointment appointment = new Appointment();
		appointment.setDate(new Date(System.currentTimeMillis() + 86400000)); // Tomorrow

		HttpEntity<Appointment> appRequest = new HttpEntity<>(appointment, getHeaders("admin", "admin"));
		ResponseEntity<Appointment> appResponse = restTemplate.postForEntity("/api/appointments/" + doctorId + "/" + patientId, appRequest, Appointment.class);
		assertThat(appResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testUnauthorizedAccess() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/doctors", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}
}