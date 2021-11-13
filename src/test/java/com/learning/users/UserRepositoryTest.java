package com.learning.users;

import com.learning.users.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utility.UtilPersonal;

public class UserRepositoryTest {

	@Test
	public void shouldCreateUser() {

		UtilPersonal utilPersonal = new UtilPersonal();
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Robinson");
		user.setLastName("Carvalho");
		user.setBirthDay(utilPersonal.formattingDate("19-3-1983"));
		user.setEmail("robinsonpc@hotmail.com");
		user.setGender(User.Gender.MALE);
		user.setMaritalStatus(User.MaritalStatus.MARRIED);
		user.setIdPartner(2);

		//when
		userRepository.create(user);

		//then
		Assertions.assertEquals(1, userRepository.count());
	}

	@Test
	public void shouldReadUser(){

		UtilPersonal utilPersonal = new UtilPersonal();
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user1 = new User();
		user1.setFirstName("Robinson");
		user1.setLastName("Carvalho");
		user1.setBirthDay(utilPersonal.formattingDate("19-3-1983"));
		user1.setEmail("robinsonpc@hotmail.com");
		user1.setGender(User.Gender.MALE);
		user1.setMaritalStatus(User.MaritalStatus.MARRIED);
		user1.setIdPartner(2);
		userRepository.create(user1);

		//when
		User userReturn1 = userRepository.read(1);
		User userReturn2 = userRepository.read(2);

		//then
		Assertions.assertEquals(user1.getEmail(), userReturn1.getEmail());
		Assertions.assertNull(userReturn2);

	}

	@Test
	public void shouldUpdateUser(){

		UtilPersonal utilPersonal = new UtilPersonal();
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Robinson");
		user.setLastName("Carvalho");
		user.setBirthDay(utilPersonal.formattingDate("19-3-1983"));
		user.setEmail("robinsonpc@hotmail.com");
		user.setGender(User.Gender.MALE);
		user.setMaritalStatus(User.MaritalStatus.MARRIED);
		user.setIdPartner(2);
		userRepository.create(user);

		//when
		user.setFirstName("RobinsonUpdated");
		userRepository.update(user);

		//then
		Assertions.assertNotEquals("Robinson", user.getFirstName());

	}

	@Test
	public void shouldDeleteUser(){

		UtilPersonal utilPersonal = new UtilPersonal();
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Robinson");
		user.setLastName("Carvalho");
		user.setBirthDay(utilPersonal.formattingDate("19-3-1983"));
		user.setEmail("robinsonpc@hotmail.com");
		user.setGender(User.Gender.MALE);
		user.setMaritalStatus(User.MaritalStatus.MARRIED);
		user.setIdPartner(2);
		userRepository.create(user);

		//when
		userRepository.delete(user.getId());
		userRepository.read(user.getId());

		//then
		Assertions.assertNotNull(user);
		Assertions.assertEquals(0, userRepository.count());

	}

	@Test
	public void shouldCheckEmailIntegrity(){

		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		UtilPersonal utilPersonal = new UtilPersonal();
		UserRepository userRepository = new UserRepositoryInMemory();

		//given
		User user = new User();
		user.setFirstName("Bruna");
		user.setLastName("Carvalho");
		user.setBirthDay(utilPersonal.formattingDate("31-01-2000"));
		user.setEmail("brunecca@bol.com.br");
		user.setGender(User.Gender.MALE);
		user.setMaritalStatus(User.MaritalStatus.MARRIED);
		user.setIdPartner(10);

		//when
		userRepository.create(user);
		Matcher matcher = pattern.matcher(user.getEmail());

		//then
		Assertions.assertTrue(matcher.matches());

	}
}
