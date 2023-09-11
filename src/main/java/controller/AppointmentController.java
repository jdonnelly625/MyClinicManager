package controller;

import manager.RegistrationManager;
import model.Appointment;
import model.Clinician;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final RegistrationManager registrationManager;

    @Autowired
    public AppointmentController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Map<String, Object>> makeAppointment(@RequestBody Map<String, Object> appointmentData) {
        String patientId = (String) appointmentData.get("patientId");
        String clinicianId = (String) appointmentData.get("clinicianId");
        LocalDateTime datetime = LocalDateTime.parse(appointmentData.get("datetime").toString());

        Patient patient = registrationManager.getPatientById(patientId);
        Clinician clinician = registrationManager.getClinicianById(clinicianId);

        if (patient == null || clinician == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Patient or Clinician not found"));
        }

        Appointment appointment = new Appointment(datetime, patient, clinician);

        try {
            registrationManager.makeAppointment(appointment);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Appointment created successfully.");
            response.put("appointment", appointment);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to create appointment.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = registrationManager.getAppointments();

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }



}
