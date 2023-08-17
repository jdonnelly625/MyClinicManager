package model;

import access.FullPatientInfoAccess;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "clinicians")
public class Clinician extends Staff implements FullPatientInfoAccess {
    /**
     * Constructs a User and sets all fields to parameters
     *
     * @param firstName of user
     * @param lastName  of user
     * @param id        user's id
     * @param email     user's email
     * @param password  hashed password of user
     * @param jobTitle the job title
     */
    public Clinician(String firstName, String lastName, String email, String password, String jobTitle) {
        super(firstName, lastName, email, password, jobTitle);
    }

    @Override
    public List<String> getPatientMedications(Patient patient) {
        return null;
    }

    @Override
    public List<String> getProviderNotes(Patient patient) {
        return null;
    }
}
