package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;




import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import com.revature.model.Users;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;
import com.revature.util.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock private UserRepository userRepository;

	private UserService underTest;
	
	@BeforeEach
	void setUp() {
		underTest = new UserService(userRepository);
		
	}
	
	Users user = new Users(
			1,
			"user1",
			"pass1",
			"user1@email.com",
			"i love sde",
			90778,
			"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
			);
	
	@Test
	void CanRegister() {
		// when
		underTest.register(user);
		
		// then
		ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);
		verify(userRepository).saveAndFlush(userArgumentCaptor.capture());
		
		Users capturedUser = userArgumentCaptor.getValue();
		assertThat(capturedUser).isEqualTo(user);
	}
	
	@Test
	void CanGetListOfUsers() {
		// when
		underTest.getAllUsers();
		// then
		verify(userRepository).findAll();
	}
	
	@Test
	void CanDoLoginByUsernameAndPassword() throws UserNotFoundException {
		//given
		given(userRepository.findByUsernameAndPassword("user1", "pass1")).willReturn(Optional.of(user));
		//when
		Users result = underTest.login(user);

		assertThat(result).isNotNull();
		verify(userRepository).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	@Test
	void CanGetUserById() throws UserNotFoundException {
		// given
		given(userRepository.findById(1)).willReturn(Optional.of(user));
		// when
		Users expected = underTest.getUser(user.getUser_id());
		// then
		assertThat(expected).isNotNull();
		verify(userRepository, times(1)).findById(1);

	}
	
	@Test
	void CanUpdateUser() throws UserNotFoundException {
		// given
		given(userRepository.findById(1)).willReturn(Optional.of(user));
		given(userRepository.save(user)).willReturn(user);
		user.setPassword("pass2");
		user.setDescription("i love sw");
		user.setZipcode(90779);
		// when
		underTest.updateUser(1, user);
		// then
		ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);
		verify(userRepository).save(userArgumentCaptor.capture());
		
		Users capturedUser = userArgumentCaptor.getValue();
		assertThat(capturedUser).isEqualTo(user);
	}
	
	@Test
	void CanDeleteUser() throws UserNotFoundException {
		//when
		underTest.deleteUser(1);
		verify(userRepository, times(1)).deleteById(1);
	}
}
