package com.learning.users;

import com.learning.users.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserRepositoryTest {

	@Test
	public void create(Object p) {
		
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Robinson");
		user.setLastName("Carvalho");
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fd = dt.format(dtf);
		user.setBirthDay(fd);
		user.setEmail("robinsonpc@hotmail.com");
		user.setGender(Person.Gender.MALE.name());
		user.setMaritalStatus(Person.MaritalStatus.MARRIED.name());
		user.setPermissionType(Person.Permission.DEVELOPER.name());
		user.setIdPartner(10);

		//when
		userRepository.create(user);

		//then
		Assertions.assertEquals(1, userRepository.count());
	}

	@Test
	public void emailCheck(String emailAddress){

		Email email = new Email();
		Assertions.assertTrue(email.validateEmail(emailAddress));

	}
}
