package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Represents an Administrator in the system, a specialized type of Staff with additional job-related attributes and behaviors.
 *
 * Administrators members are more specialized to have a unique fields but only have inherited basic patient access from Staff parent.
 *
 * This is an Entity that corresponds to the "administrators" table in the database.
 *
 */
@Entity
@Table(name = "administrators")
public class Administrator extends Staff {



    /**
     * Default constructor
     */
    public Administrator() {
        super();

    }
    /**
     * Constructs a User and sets all fields to parameters
     *
     * @param firstName of user
     * @param lastName  of user
     * @param id        user's id
     * @param email     user's email
     * @param password  hashed password of user
     */
    public Administrator(String firstName, String lastName, String email, String password, String jobTitle) {
        super(firstName, lastName, email, password, jobTitle);
    }


}
