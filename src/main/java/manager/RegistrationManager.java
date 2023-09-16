package manager;

import directories.AppointmentDirectory;
import directories.PatientDirectory;
import directories.StaffDirectory;
import jakarta.annotation.PostConstruct;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RegistrationManager {

    private final StaffDirectory staffDirectory;
    private final PatientDirectory patientDirectory;
    private final AppointmentDirectory appointmentDirectory;
    private User currentUser;

    @Autowired
    public RegistrationManager(StaffDirectory staffDirectory, PatientDirectory patientDirectory, AppointmentDirectory appointmentDirectory) {
        this.staffDirectory = staffDirectory;
        this.patientDirectory = patientDirectory;
        this.appointmentDirectory = appointmentDirectory;
        this.currentUser = null;
    }

    /**
     * After construction set the default administrator as able to login for demo purposes
     */
    @PostConstruct
    public void init() {
        if(staffDirectory.getStaffByEmail("admin@example.com") == null) {
            Administrator admin = new Administrator("Admin", "User", "admin@example.com", "adminPassword", "admin");
            System.out.println("inside init");
            try {
                registerAdministrator(admin);
            } catch (Exception e) {
                e.printStackTrace();
                // the admin account already exists, no need to create a new one
            }
        }

    }


    public Patient registerPatient(Patient patient) {
        patientDirectory.addPatient(patient);
        return patient;
    }

    public List<Patient> getRegisteredPatients() {
        return patientDirectory.getAllPatients();
    }

    public Patient deregisterPatient(Patient patient) {
        patientDirectory.removePatient(patient);
        return patient;
    }

    public Staff deregisterStaffById(String staffId) {
        return staffDirectory.removeStaffById(staffId);
    }

    //Uses email for username for now
    public User login(String username, String password) {
        System.out.println("inside login");
        String localHashPW = hashPW(password);


        Patient p = patientDirectory.getPatientByEmail(username);
        Staff s = staffDirectory.getStaffByEmail(username);

        if (p != null) {
            if (p.getPassword().equals(localHashPW)) {
                currentUser = p;
                return p;
            }

        }
        else if (s != null) {
            System.out.println("Inside checking if staff password equals local hash");
            System.out.println("s.getPassword: " + s.getPassword());
            System.out.println("localHash: " + localHashPW);

            if (s.getPassword().equals(localHashPW)) {
                currentUser = s;
                return s;
            }
        }
        System.out.println("Throwing exception");
        throw new IllegalArgumentException("User doesn't exist.");

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

    public Appointment makeAppointment(Appointment appointment) {
        appointmentDirectory.makeAppointment(appointment);
        return appointment;
    }

    public List<Appointment> getAppointments() {
        return appointmentDirectory.getAppointments();
    }

    public List<Patient> getPatientByLastName(String lastName) {
        return patientDirectory.getByLastName(lastName);
    }

    public Staff registerClinician(Clinician clinician) {
        return staffDirectory.addClinician(clinician);
    }

    public Staff registerAdministrator(Administrator administrator) {
        System.out.println("Registering administrator");
        return staffDirectory.addAdministrator(administrator);
    }

    public List<Staff> getRegisteredStaff() {
        return staffDirectory.getAllStaff();
    }

    public List<Clinician> getClinicianByLastName(String lastName) {

        return staffDirectory.getCliniciansByLastName(lastName);
    }

    public Patient getPatientById(String patientId) {

        return patientDirectory.getPatientById(patientId);
    }

    public Clinician getClinicianById(String clinicianId) {

        return staffDirectory.getClinicianById(clinicianId);
    }

    /**
     * Provides a more user friendly key value pairing of appointment information for all appointments
     * @return List a list of maps with appointment key value pairs formatted for tables/client readability
     */
    public List<Map<String,String>> getAppointmentInfoMap(List<Appointment> appointmentList) {


        List<Map<String, String>> appointments = new ArrayList<>();

        for(Appointment a : appointmentList) {
            HashMap<String, String> keys = new HashMap<>();
            keys.put("id", String.valueOf(a.getId()));
            LocalDateTime localDateTime = a.getAppointmentTime();
            String day = localDateTime.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            keys.put("day", day);
            String time = localDateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
            keys.put("time", time);
            keys.put("patient", a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
            keys.put("clinician", a.getClinician().getFirstName() + " " + a.getClinician().getLastName());
            keys.put("status", a.getStatus().name());
            appointments.add(keys);

        }
        return appointments;


    }

    public Map<String,String> getAppointmentInfoMapSingular(Appointment a) {


        HashMap<String, String> keys = new HashMap<>();
        keys.put("id", String.valueOf(a.getId()));
        LocalDateTime localDateTime = a.getAppointmentTime();
        String day = localDateTime.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
        keys.put("day", day);
        String time = localDateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        keys.put("time", time);
        keys.put("patient", a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
        keys.put("clinician", a.getClinician().getFirstName() + " " + a.getClinician().getLastName());
        keys.put("status", a.getStatus().name());

        return keys;


    }

    public List<Clinician> getAllClinicians() {
        return staffDirectory.getAllClinicians();
    }

    public List<Appointment> filterAppointments(String clinicianId, String status, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return appointmentDirectory.getFilteredAppointments(clinicianId, status, dateStart, dateEnd);
    }



    public Appointment getAppointmentById(Long appointmentId) {

        return appointmentDirectory.getAppointmentById(appointmentId);
    }

    public Appointment updateAppointment(Long appointmentId, LocalDateTime datetime, Patient patient, Clinician clinician, String status) {

        return appointmentDirectory.updateAppointment(appointmentId, datetime, patient, clinician, status);
    }
}
