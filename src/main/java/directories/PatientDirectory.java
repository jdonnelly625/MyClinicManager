package directories;

import jakarta.transaction.Transactional;
import model.Patient;
import org.springframework.stereotype.Service;
import repository.AppointmentRepository;
import repository.PatientRepository;

import java.util.List;

@Service
@Transactional
public class PatientDirectory {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientDirectory(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {

        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
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
        appointmentRepository.deleteByPatient(patient);
        patientRepository.delete(patient);
    }

    public Patient getPatientByEmail(String email) {
        for(Patient p : getAllPatients()) {
            if(email.equals(p.getEmail())) return p;
        }
        return null;
    }

    public List<Patient> getByLastName(String lastName) {

        List<Patient> patients = patientRepository.findByLastNameIgnoreCase(lastName);
        return patients;
    }

    public Patient getPatientById(String patientId) {
        return patientRepository.findById(patientId).get();
    }


}
