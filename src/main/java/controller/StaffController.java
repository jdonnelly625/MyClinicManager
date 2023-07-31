package controller;

import manager.RegistrationManager;
import model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final RegistrationManager registrationManager;

    @Autowired
    public StaffController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Staff> registerStaff(@RequestBody Staff staff) {
        Staff registeredStaff = registrationManager.registerStaff(staff);
        return new ResponseEntity<>(registeredStaff, HttpStatus.CREATED);
    }

}
