package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	
	@Test
	void CanRegister() {
		//given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sde",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
		
		// when
		underTest.register(user);
		
		// then
		ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);
		verify(userRepository).saveAndFlush(userArgumentCaptor.capture());
		
		Users capturedUser = userArgumentCaptor.getValue();
		assertThat(capturedUser).isEqualTo(user);
	}
	
	@Test
	void CanGetAllUsers() {
		// when
		underTest.getAllUsers();
		// then
		verify(userRepository).findAll();
	}
	
	@Test
	void CanDoLogin() throws UserNotFoundException {
		//given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sde",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
		given(userRepository.findByUsernameAndPassword("user1", "pass1")).willReturn(Optional.of(user));
		//when
		Users result = underTest.login(user);
		System.out.println(result);
		assertThat(result).isNotNull();
		verify(userRepository).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	@Test
	void CanGetUser() throws UserNotFoundException {
		// given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sde",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
		
		given(userRepository.findById(1)).willReturn(Optional.of(user));
		// when
		Users expected = underTest.getUser(user.getUser_id());
		// then
		assertThat(expected).isNotNull();
		verify(userRepository, times(1)).findById(1);

	}
	
	@Test
	@Disabled
	void CanUpdateUser() throws UserNotFoundException {
		// given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sd",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
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
	@Disabled
	void CanDeleteUser() throws UserNotFoundException {
		//given
		Users user = new Users(
				1,
				"user1",
				"pass1",
				"user1@email.com",
				"i love sde",
				90778,
				"https://dl.dropboxusercontent.com/s/av08po3cupbll0g/chrome_Qtl2u3xBZy.jpg"
				);
		//when
		underTest.deleteUser(user.getUser_id());
		verify(userRepository, times(1)).deleteById(user.getUser_id());
		assertThat(user).isNull();
	}
}
