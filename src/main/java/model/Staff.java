package model;

import access.BasicPatientInfoAccess;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Represents a staff member in the system, a specialized type of User with additional job-related attributes and behaviors.
 *
 * Staff members are more specialized to have a specific job title and the ability to access basic information about patients.
 * This class is abstract, as it's intended to be subclassed by specific staff roles or categories.
 * Implements BasicPatientInfoAccess to determine access level to certain patient records. Certain Staff subclasses may implement more permissions.
 * This is an Entity that corresponds to the "staff" table in the database.
 *
 */
@Entity
@Table(name = "staff")
public abstract class Staff extends User implements BasicPatientInfoAccess {
    /** ID unique to employee */
    private String jobTitle;

    /**
     * No argument constructor for Staff that defers to with super() to parent constructor.
     */
    public Staff() { super(); }

    /**
     * Constructs a User and sets all fields to parameters
     *
     * @param firstName of user
     * @param lastName  of user
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
