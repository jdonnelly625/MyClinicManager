package controller;

import manager.RegistrationManager;
import model.ErrorDetails;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final RegistrationManager registrationManager;

    @Autowired
    public LoginController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }


    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        if(username == null || password == null) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage("Must enter username or password.");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(errorDetails);

        }

        try {
            User user = registrationManager.login(username, password);

            Map<String, Object> response = new HashMap<>();
            response.put("name", user.getFirstName());
            response.put("loggedIn", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(IllegalArgumentException e) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage("User not found. Check username and password.");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(errorDetails);
        }
    }



}
