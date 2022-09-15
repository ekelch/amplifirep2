package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);
	
	Optional<User> findByUsername(String username);
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM users u WHERE u.id - :userId")
	Boolean isUserExistById(@Param("userId") Long id);
	
}
