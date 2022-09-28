package com.revature.service;

import java.util.List;
import java.util.Optional;



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

	public Users register(Users user) {
		return userRepository.saveAndFlush(user);
	}

	public List<Users> getUsers(){
		return userRepository.findAll();

	}


	public Users login(Users user) throws UserNotFoundException {
		Optional<Users> existUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(existUser.isPresent()) {
			
			Users actualUser = existUser.get();
			return new Users(
					actualUser.getUser_id(), 
					actualUser.getUsername(), 
					actualUser.getPassword(), 
					actualUser.getEmail(), 
					actualUser.getDescription(),
					actualUser.getZipcode(),
					actualUser.getPhoto_url());
<<<<<<< HEAD
					
=======
>>>>>>> a0886dedd99975cd3b82cb76c49fc4266dba71ed
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
	
	public void updateUser(Integer id, Users user) throws UserNotFoundException {
		Users updateUser = this.getUser(id);
		
		updateUser.setPassword(user.getPassword());
		updateUser.setDescription(user.getDescription());
		updateUser.setZipcode(user.getZipcode());
		updateUser.setPhoto_url(user.getPhoto_url());
		
		userRepository.save(updateUser);
	}
	
	public void deleteUser(Integer id) throws UserNotFoundException {
		userRepository.deleteById(id);
		
	}
	
	
}