package directories;

import model.Appointment;
import org.springframework.stereotype.Service;
import repository.AppointmentRepository;
import repository.PatientRepository;

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


    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
}
