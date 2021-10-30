package com.learning.users;

import com.learning.users.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepositoryTest {

	@Test
	public void create() {
		
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
	public void emailCheck(){

		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Robinson");
		user.setLastName("Carvalho");
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fd = dt.format(dtf);
		user.setBirthDay(fd);
		user.setEmail("@bol.com.br");
		user.setGender(Person.Gender.MALE.name());
		user.setMaritalStatus(Person.MaritalStatus.MARRIED.name());
		user.setPermissionType(Person.Permission.DEVELOPER.name());
		user.setIdPartner(10);

		//when
		userRepository.create(user);
		Matcher matcher = pattern.matcher(user.getEmail());

		//then
		Assertions.assertTrue(matcher.matches());

	}
}
