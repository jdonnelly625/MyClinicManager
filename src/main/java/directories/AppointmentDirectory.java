package directories;

import model.Appointment;
import model.Clinician;
import model.Patient;
import org.springframework.stereotype.Service;
import repository.AppointmentRepository;
import repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentDirectory {

    private final AppointmentRepository appointmentRepository;

    public AppointmentDirectory(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void makeAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);

    }

    /**
     * Gets the appointments and orders them by time ascending
     * @return a list of all appointments in ascending order
     */
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAllByOrderByAppointmentTimeAsc();
    }

    /**
     * Method to filter appointments based on different criteria
     * @param clinicianId the clinician id
     * @param statusStr the string representation of status enum
     * @param dateStart the start date
     * @param dateEnd the end date
     * @return filtered appointment list ordered by ascending appointment time
     */
    public List<Appointment> getFilteredAppointments(String clinicianId, String statusStr, LocalDateTime dateStart, LocalDateTime dateEnd) {
        Appointment.Status status = (statusStr != null && !statusStr.isEmpty()) ? Appointment.Status.valueOf(statusStr.toUpperCase()) : null;
        if ((clinicianId == null || clinicianId.isEmpty()) && status == null && dateStart == null && dateEnd == null) {
            return getAppointments();
        }

        if (clinicianId != null && !clinicianId.isEmpty() && status != null && dateStart != null && dateEnd != null) {
            return appointmentRepository.findByClinicianIdAndStatusAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(clinicianId, status, dateStart, dateEnd);
        } else if (clinicianId != null && !clinicianId.isEmpty() && status != null) {
            return appointmentRepository.findByClinicianIdAndStatusOrderByAppointmentTimeAsc(clinicianId, status);
        } else if (clinicianId != null && !clinicianId.isEmpty() && dateStart != null && dateEnd != null) {
            return appointmentRepository.findByClinicianIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(clinicianId, dateStart, dateEnd);
        } else if (status != null && dateStart != null && dateEnd != null) {
            return appointmentRepository.findByStatusAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(status, dateStart, dateEnd);
        } else if (clinicianId != null && !clinicianId.isEmpty()) {
            return appointmentRepository.findByClinicianIdOrderByAppointmentTimeAsc(clinicianId);
        } else if (status != null) {
            return appointmentRepository.findByStatusOrderByAppointmentTimeAsc(status);
        } else if (dateStart != null && dateEnd != null) {
            return appointmentRepository.findByAppointmentTimeBetweenOrderByAppointmentTimeAsc(dateStart, dateEnd);
        }
        return new ArrayList<>();
    }


    public Appointment updateAppointment(Long appointmentId, LocalDateTime datetime, Patient patient, Clinician clinician, String status) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found based on id: " + appointmentId));

        if(patient != null) appointment.setPatient(patient);
        if(clinician != null) appointment.setClinician(clinician);
        if(datetime != null) appointment.setAppointmentTime(datetime);
        if(status != null) appointment.setStatus(Appointment.Status.valueOf(status.toUpperCase()));

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long appointmentId) {

        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found for this id: " + appointmentId));
    }
}
