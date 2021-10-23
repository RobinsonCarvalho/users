package com.learning.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;

public class UserRepositoryTest {

	@Test
	public void createUserSuccessfully() {
		
		UserRepository userRepository = new UserRepositoryInMemory();
		//given
		User user = new User();
		user.setName("Robinson");
		user.setEmail("robinsonpc@hotmail.com");
		user.setAddress("Summerhill North, Redgates");
		
		User user1 = new User();
		user1.setName("Claudenir");
		user1.setEmail("claudenir@hotmail.com");
		user1.setAddress("Wilton");
		
		//when
		userRepository.create(user);
		userRepository.create(user1);
		//then
		Assertions.assertEquals(2, userRepository.count());
	}
	
}
