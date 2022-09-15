package repository;

import org.springframework.data.repository.CrudRepository;

import repository.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	
}
