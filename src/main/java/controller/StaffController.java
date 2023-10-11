package controller;

import manager.RegistrationManager;
import model.Administrator;
import model.Clinician;
import model.Patient;
import model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/staff")
public class StaffController {

    private final RegistrationManager registrationManager;

    @Autowired
    public StaffController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Staff> registerStaff(@RequestBody Map<String, Object> staffData) {
        Staff registeredStaff = null;

        String role = (String) staffData.get("role");
        String firstName = (String) staffData.get("firstName");
        String lastName = (String) staffData.get("lastName");
        String email = (String) staffData.get("email");
        String password = (String) staffData.get("password");
        String jobTitle = (String) staffData.get("jobTitle");

        if ("clinician".equals(role)) {
            Clinician clinician = new Clinician(firstName, lastName, email, password, jobTitle);
            registeredStaff = registrationManager.registerClinician(clinician);
        } else if ("administrator".equals(role)) {
            Administrator administrator = new Administrator(firstName, lastName, email, password, jobTitle);
            registeredStaff = registrationManager.registerAdministrator(administrator);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid role
        }

        if (registeredStaff != null) {
            return new ResponseEntity<>(registeredStaff, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deregister")
    public ResponseEntity<Staff> deregisterStaff(@RequestBody Map<String, Object> staffData) {

        Staff deregisteredStaff = registrationManager.deregisterStaffById((String) staffData.get("id"));
        return new ResponseEntity<>(deregisteredStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getRegisteredStaff() {
        List<Staff> staff = registrationManager.getRegisteredStaff();
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("clinicianNames")
    public ResponseEntity<List<Map<String, Object>>> getClinicianNames() {
        List<Clinician> clinicians = registrationManager.getAllClinicians();
        List<Map<String, Object>> clinicianData = new ArrayList<>();

        for(Clinician c: clinicians) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", c.getId());
            data.put("name", c.getFirstName() + " " + c.getLastName());
            clinicianData.add(data);
        }

        return new ResponseEntity<>(clinicianData, HttpStatus.OK);
    }





    @GetMapping("/searchClinician")
    public ResponseEntity<List<Clinician>> searchClinicianByLastName(@RequestParam String lastName) {
        List<Clinician> clinicians = registrationManager.getClinicianByLastName(lastName);
        if (clinicians.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clinicians, HttpStatus.OK);
    }


}
