package repository;

import model.Clinician;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicianRepository extends JpaRepository<Clinician, String> {

    List<Clinician> findByLastNameIgnoreCase(String lastName);
}
