package manager;

import directories.PatientDirectory;
import directories.StaffDirectory;
import jakarta.annotation.PostConstruct;
import model.Administrator;
import model.Patient;
import model.Staff;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class RegistrationManager {

    private final StaffDirectory staffDirectory;
    private final PatientDirectory patientDirectory;
    private User currentUser;

    @Autowired
    public RegistrationManager(StaffDirectory staffDirectory, PatientDirectory patientDirectory) {
        this.staffDirectory = staffDirectory;
        this.patientDirectory = patientDirectory;
        this.currentUser = null;
    }

    /**
     * After construciton set the default administrator as able to login
     */
    @PostConstruct
    public void init() {
        Staff admin = new Administrator("Admin", "User", "admin@example.com", "adminPassword", "admin");
        System.out.println("inside init");
        try {
            registerStaff(admin);
        } catch (Exception e) {
            // the admin account already exists, no need to create a new one
        }
    }

    public Staff registerStaff(Staff staff) {
        return staffDirectory.addStaff(staff);
    }


    public Patient registerPatient(Patient patient) {
        patientDirectory.addPatient(patient);
        return patient;
    }

    public List<Patient> getRegisteredPatients() {
        return patientDirectory.getAllPatients();
    }

    public Patient deregisterPatient(Patient patient) {
        patientDirectory.removePatient(patient);
        return patient;
    }

    //Uses email for username for now
    public User login(String username, String password) {
        System.out.println("inside login");
        String localHashPW = hashPW(password);


        Patient p = patientDirectory.getPatientByEmail(username);
        Staff s = staffDirectory.getStaffByEmail(username);

        if (p != null) {
            if (p.getPassword().equals(localHashPW)) {
                currentUser = p;
                return p;
            }

        }
        else if (s != null) {
            System.out.println("Inside checking if staff password equals local hash");
            System.out.println("s.getPassword: " + s.getPassword());
            System.out.println("localHash: " + localHashPW);

            if (s.getPassword().equals(localHashPW)) {
                currentUser = s;
                return s;
            }
        }
        else {
            throw new IllegalArgumentException("User doesn't exist.");
        }

        return null;
    }

    /**
     * Basic hashing algorithm without using salt
     * @param pw
     * @return
     */
    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance("SHA-256");
            digest1.update(pw.getBytes());
            return Base64.getEncoder().encodeToString(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }

}
