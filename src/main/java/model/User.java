package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

/**
 * An abstract class to store information and methods for users of the ClinicRegistrationApplication system.
 * @author Joshua Donnelly
 *
 */
public abstract class User {
    /** User's first name. */
    private String firstName;
    /** User's last name. */
    private String lastName;
    /** User's id. */
    private String id;
    /** User's email. */
    private String email;
    /** User's password. */
    private String password;

    /**
     * Constructs a User and sets all fields to parameters
     * @param firstName of user
     * @param lastName of user
     * @param email user's email
     * @param password hashed password of user
     */
    public User(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        this.id = generateId();
        setEmail(email);
        setPassword(password);
    }

    /**
     * Generates an ID for a user
     * @return ID
     */
    private String generateId() {
        Random random = new Random();
        int id = random.nextInt(90000) + 10000;  // This will generate random 5-digit numbers (from 10000 to 99999)
        return String.valueOf(id);
    }

    /**
     * Return's user's email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set's user email
     * @param email the email to set
     * @throws IllegalArgumentException if invalid email
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (email.indexOf("@") > email.lastIndexOf(".")){
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;

    }

    /**
     * Return's the hashed password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set's user hashed password
     * @param password the password to set
     * @throws IllegalArgumentException for invalid password
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.password = hashPW(password);
    }

    /**
     * Basic hashing algorithm without using salt
     * @param pw
     * @return
     */
    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance("SHA-256");
            digest1.update(pw.getBytes());
            return Base64.getEncoder().encodeToString(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }

    /**
     * Set's the first name of user
     * @param firstName the firstName to set
     * @throws IllegalArgumentException if invalid first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Invalid first name");
        }
        this.firstName = firstName;
    }

    /**
     * Return's user first name
     * @return firstName the firstName to set
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set's user last name
     * @param lastName the lastName to set
     * @throws IllegalArgumentException if invalid last name
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid last name");
        }
        this.lastName = lastName;
    }

    /**
     * Return's user last name
     * @return lastName the last name to set
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set's the user's id
     * @param id the id to set
     * @throws IllegalArgumentException if invalid id
     */
    private void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Invalid id");
        }
        this.id = id;
    }

    /**
     * Return's user id
     * @return id the id of user
     */
    public String getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id, email, password);
    }
}
