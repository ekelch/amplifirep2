package com.revature.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Id>{

	List<Users> findAll();
	
	Optional<Users> findByUsernameAndPassword(String username, String password);
}
