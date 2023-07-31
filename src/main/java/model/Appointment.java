package model;

import java.time.LocalDateTime;

public class Appointment {

    public enum Status {
        UPCOMING,
        CANCELLED,
        COMPLETED
    }

    private LocalDateTime appointmentTime;
    private Status status;
    private Patient patient;
    private Clinician clinician;

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

}
