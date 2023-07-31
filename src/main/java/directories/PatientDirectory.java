package directories;

import model.Patient;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PatientDirectory {
    /** The list of all registered patients */
    private LinkedList<Patient> patientDirectory;

    /** Constructs a new empty list of patients */
    public PatientDirectory() {
        patientDirectory = new LinkedList<Patient>();
    }
    /** Adds a patient */
    public Patient addPatient(Patient patient) {
        patientDirectory.add(patient);
        return patient;
    }

    /** Gets all patients from the directory */
    public List<Patient> getAllPatients() {
        return patientDirectory;
    }

    public void removePatient(Patient patient) {
        patientDirectory.remove(patient);
    }

    public Patient getPatientByEmail(String email) {
        for(Patient p : patientDirectory) {
            if(email.equals(p.getEmail())) return p;
        }
        return null;
    }
}
