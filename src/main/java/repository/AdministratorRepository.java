package repository;

import model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository provides CRUD operations for Administrator entities, which are a child class of User and type of Staff.
 * It extends the JPARepository to inherit standard database operations and leverage the power of method naming conventions
 * for default implementations of common database operations. However, custom queries can be added with @Query tag.
 *
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {

}
