package model;

import access.BasicPatientInfoAccess;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "staff")
public abstract class Staff extends User implements BasicPatientInfoAccess {
    /** ID unique to employee */
    private String jobTitle;



    /**
     * Constructs a User and sets all fields to parameters
     *
     * @param firstName of user
     * @param lastName  of user
     * @param id        user's id
     * @param email     user's email
     * @param password  hashed password of user
     * @param jobTitle job title of staff
     */
    public Staff(String firstName, String lastName, String email, String password, String jobTitle) {
        super(firstName, lastName, email, password);
        setJobTitle(jobTitle);

    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }


    public String getPatientName(Patient patient) {
        return null;
    }


    public List<Appointment> getPatientAppointments(Patient patient) {
        return null;
    }

}
