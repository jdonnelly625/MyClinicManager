package repository;

import model.Clinician;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This repository provides CRUD operations for general User entities if required.
 * It extends the JPARepository to inherit standard database operations and leverage the power of method naming conventions
 * for default implementations of common database operations. However, custom queries can be added with @Query tag.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
