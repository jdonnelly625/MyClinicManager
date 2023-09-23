package repository;

import model.Appointment;
import model.Clinician;
import model.Patient;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This repository provides CRUD operations for Appointment entities, including find operations filtered by fields and ordered by datetime.
 * It extends the JPARepository to inherit standard database operations and leverage the power of method naming conventions
 * for default implementations of common database operations. However, custom queries can be added with @Query tag.
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByOrderByAppointmentTimeAsc();

    List<Appointment> findByClinicianIdOrderByAppointmentTimeAsc(String clinicianId);

    List<Appointment> findByClinicianIdAndStatusOrderByAppointmentTimeAsc(String clinicianId, Appointment.Status status);

    List<Appointment> findByClinicianIdAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(String clinicianId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByStatusOrderByAppointmentTimeAsc(Appointment.Status status);

    List<Appointment> findByStatusAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(Appointment.Status status, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByAppointmentTimeBetweenOrderByAppointmentTimeAsc(LocalDateTime start, LocalDateTime end);

    List<Appointment> findByClinicianIdAndStatusAndAppointmentTimeBetweenOrderByAppointmentTimeAsc(String clinicianId, Appointment.Status status, LocalDateTime dateStart, LocalDateTime dateEnd);

    void deleteByPatient(Patient patient);

    void deleteByClinician(Clinician clinician);
}


