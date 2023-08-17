package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient extends User {


    /**
     * Default constructor
     */
    public Patient() {
        super();

    }
    /**
     * Constructs a User and sets all fields to parameters
     *
     * @param firstName of user
     * @param lastName  of user
     * @param email     user's email
     * @param password  hashed password of user
     */
    public Patient(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}

