package controller;

import dto.AppointmentUpdateRequest;
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
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final RegistrationManager registrationManager;

    @Autowired
    public AppointmentController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Map<String, Object>> makeAppointment(@RequestBody Map<String, Object> appointmentData) {

        //Converts Map into Appointment
        Appointment appointment = registrationManager.convertAppointmentData(appointmentData);
        //Makes the appointment
        registrationManager.makeAppointment(appointment);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Appointment created successfully.");
        response.put("appointment", appointment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

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
    public ResponseEntity<Map<String, Object>> updateAppointment(
            @PathVariable(value = "id") Long appointmentId,
            @RequestBody AppointmentUpdateRequest request) {

        Appointment updatedAppointment = registrationManager.updateAppointment(
                appointmentId,
                request.getDatetime(),
                request.getPatientId(),
                request.getClinicianId(),
                request.getStatus()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Appointment updated successfully.");
        response.put("appointment", updatedAppointment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Map<String, String>> getAppointmentById(@PathVariable(value = "id") Long appointmentId) {

        Appointment appt = registrationManager.getAppointmentById(appointmentId);

        Map<String, String> map = registrationManager.getAppointmentInfoMapSingular(appt);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }


}
