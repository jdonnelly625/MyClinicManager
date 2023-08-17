package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "administrators")
public class Administrator extends Staff {



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
