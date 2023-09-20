package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends User {

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonBackReference
//    private List<Appointment> appointments;


    /**
     * Default constructor
     */
    public Patient() {
        super();
        //this.appointments = new HashSet<>();

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

