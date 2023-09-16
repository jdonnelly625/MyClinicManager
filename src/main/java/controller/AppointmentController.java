package controller;

import manager.RegistrationManager;
import model.Appointment;
import model.Clinician;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public ResponseEntity<List<Map<String, String>>> getAllAppointments() {


        List<Map<String, String>> appointments = registrationManager.getAppointmentInfoMap(registrationManager.getAppointments());



        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }



    @GetMapping("/getFilteredAppointments")
    public ResponseEntity<List<Map<String, String>>> getFilteredAppointments(@RequestParam(required = false) String clinicianId,
                                                                             @RequestParam(required = false) String status,
                                                                             @RequestParam(required = false) LocalDateTime startDate,
                                                                             @RequestParam(required = false) LocalDateTime endDate) {

        List<Appointment> filteredAppointments = registrationManager.filterAppointments(clinicianId, status, startDate, endDate);

        List<Map<String, String>> appointments = registrationManager.getAppointmentInfoMap(filteredAppointments);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateAppointment(@PathVariable(value = "id") Long appointmentId,
                                                         @RequestBody Map<String, Object> appointmentData) {
        String patientId = (String) appointmentData.get("patientId");
        String clinicianId = (String) appointmentData.get("clinicianId");
        LocalDateTime datetime;
        Object datetimeObj = appointmentData.get("datetime");
        if (datetimeObj == null || datetimeObj.toString().trim().isEmpty()) {
            datetime = null;
        } else {
            datetime = LocalDateTime.parse(datetimeObj.toString());
        }

        String status = (String) appointmentData.get("status");
        if(status == "") status = null;

        Patient patient;
        if(patientId == null) {
            patient = null;
        }
        else {
            patient = registrationManager.getPatientById(patientId);
        }

        Clinician clinician;
        if(clinicianId == null) {
            clinician = null;
        }
        else {
            clinician = registrationManager.getClinicianById(clinicianId);
        }


        try {
            Appointment updatedAppointment = registrationManager.updateAppointment(appointmentId, datetime, patient, clinician, status);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Appointment updated successfully.");
            response.put("appointment", updatedAppointment);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to create appointment.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<Map<String, String>> getAppointmentById(@PathVariable(value = "id") Long appointmentId) {
        try {
            Appointment appt = registrationManager.getAppointmentById(appointmentId);
            if(appt == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Map<String, String> map = registrationManager.getAppointmentInfoMapSingular(appt);
            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
