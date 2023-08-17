package directories;

import model.Patient;
import org.springframework.stereotype.Service;
import repository.PatientRepository;

import java.util.List;

@Service
public class PatientDirectory {
    private final PatientRepository patientRepository;

    public PatientDirectory(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    /** Adds a patient */
    public Patient addPatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    /** Gets all patients from the directory */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void removePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    public Patient getPatientByEmail(String email) {
        for(Patient p : getAllPatients()) {
            if(email.equals(p.getEmail())) return p;
        }
        return null;
    }
}
