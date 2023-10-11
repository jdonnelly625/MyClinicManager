package model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Represents an appointment in the system, storing relevant fields and implementing relevant methods to access
 * appointment information.
 *
 * This is an Entity that corresponds to the "appointments" table in the database.
 *
 */
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;


    /**
     * Uses enum to determine the status of the appointment which can be manipulated.
     */
    public enum Status {
        UPCOMING,
        CANCELLED,
        COMPLETED
    }
    @NotNull(message = "Appointment time cannot be null.")
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    /** Many appointments can be associated with a single patient. Can use this to access patient from patient
     * table based on id.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonManagedReference
    @NotNull(message = "Patient cannot be null")
    private Patient patient;

    /** Many appointments can be associated with a single clinician */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinician_id")
    @JsonManagedReference
    @NotNull(message = "Clinician cannot be null")
    private Clinician clinician;

    /** Default constructor */
    public Appointment() {

    }

    public Appointment(LocalDateTime appointmentTime, Patient patient, Clinician clinician) {
        this.appointmentTime = appointmentTime;
        this.status = Status.UPCOMING; // All appointments start as upcoming
        this.patient = patient;
        this.clinician = clinician;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public Clinician getClinician() {
        return clinician;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;

    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;

    }

    public void setAppointmentTime(LocalDateTime time) {
        this.appointmentTime = time;
    }


}
