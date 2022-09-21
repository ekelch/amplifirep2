package com.revature.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Users;
import com.revature.repository.UserRepository;
import com.revature.util.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;

	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	
	}

	public void register(Users user) {
		userRepository.saveAndFlush(user);
	}

	public List<Users> getUsers(){
		return userRepository.findAll();

	}


	public Users login(Users user) throws UserNotFoundException {
		Optional<Users> existUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(existUser.isPresent()) {
			
			Users actualUser = existUser.get();
			return new Users(actualUser.getUser_id(), actualUser.getUsername(), actualUser.getPassword());
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	
		}
	
	public Users getUser(Integer id) throws UserNotFoundException {
		Optional<Users> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	}
	
}
