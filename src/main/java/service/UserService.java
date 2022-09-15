package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.UserRepository;
import repository.entity.User;

@Service
public class UserService {

	private UserRepository userRepository;
	
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	public User save(User user) {
		return userRepository.save(user);
	}
	
}
