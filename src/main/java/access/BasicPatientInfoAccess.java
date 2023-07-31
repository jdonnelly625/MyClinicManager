package access;

import model.Appointment;
import model.Patient;

import java.util.List;

public interface BasicPatientInfoAccess {
    public String getPatientName(Patient patient);
    public List<Appointment> getPatientAppointments(Patient patient);
}
