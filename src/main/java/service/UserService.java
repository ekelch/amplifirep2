package service;

import java.util.Optional;

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
	
	public Optional<User> findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user; // do some kind of error handling here
	}
	
	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.isPresent() ? user.get() : null;
	}
	
	public boolean isExists(Long id) {
		return userRepository.isUserExistById(id);
	}
}
