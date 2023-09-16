package model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public enum Status {
        UPCOMING,
        CANCELLED,
        COMPLETED
    }

    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    /** Many appointments can be associated with a single patient. Can use this to access patient from patient
     * table based on id.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonManagedReference
    private Patient patient;

    /** Many appointments can be associated with a single clinician */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinician_id")
    @JsonManagedReference
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
