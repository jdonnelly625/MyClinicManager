package controller;

import manager.RegistrationManager;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final RegistrationManager registrationManager;

    @Autowired
    public PatientController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
        Patient registeredPatient = registrationManager.registerPatient(patient);
        return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
    }

    @PostMapping("/deregister")
    public ResponseEntity<Patient> deregisterPatient(@RequestBody Patient patient) {
        Patient deregisteredPatient = registrationManager.deregisterPatient(patient);
        return new ResponseEntity<>(deregisteredPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getRegisteredPatients() {
        List<Patient> patients = registrationManager.getRegisteredPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    //... other methods
}
