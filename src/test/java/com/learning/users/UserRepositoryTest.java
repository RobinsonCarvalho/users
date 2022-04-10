package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepositoryInMemory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDate;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Reynold");
        user.setLastName("O'Shean");
        user.setEmail("reynold.oshean.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");
        userRepositoryInMemory.create(user);
        Assertions.assertEquals(1, userRepositoryInMemory.count());
    }

    @Test
    void shouldNotCreateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("");
        user.setLastName("Batistini");
        user.setEmail("c.batistini@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com/batistini");
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> userRepositoryInMemory.create(user)
        );

    }

    @Test
    void shouldNotReplicateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        User newUser = new User();
        newUser.setName("Roy");
        newUser.setLastName("Stuart");
        newUser.setEmail("jmiller@gmail.com");
        newUser.setDateOfBirth(LocalDate.of(1988, 9, 15));
        newUser.setPhone("+353834754577");
        newUser.setGitHubProfile("http://www.linkedin.com/roy");
        Assertions.assertThrows(KeyAlreadyExistsException.class,
                ()-> userRepositoryInMemory.create(newUser));
    }

    @Test
    void shouldUpdateDataFromTheUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();

        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        user.setLastName("Miller Roosevelt");
        userRepositoryInMemory.update(user);

        User newUser;
        newUser = userRepositoryInMemory.read(user.getEmail());
        Assertions.assertEquals("Miller Roosevelt", newUser.getLastName());

    }

    @Test
    void shouldNotUpdateUserWhenFieldValueIsMissing() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);
        user.setLastName("");
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> userRepositoryInMemory.update(user)
        );

    }

    @Test
    void shouldWarnWhenUserNotFound() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Mary");
        user.setLastName("Gordon");
        user.setEmail("emailinexistent@test.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178826");
        user.setGitHubProfile("http://www.linkedin.com/marygordon");

        Assertions.assertThrows(InvalidKeyException.class,
                () -> userRepositoryInMemory.read(user.getEmail())
        );

    }

    @Test
    void shouldGetUserData() {

        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Bruce");
        user.setLastName("Twant");
        user.setEmail("brucet@gmail.com");
        user.setDateOfBirth(LocalDate.of(1975, 12, 30));
        user.setPhone("+353838547265");
        user.setGitHubProfile("http://www.linledin.com/brucetwant/");
        userRepository.create(user);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals("Bruce", user.getName()),
                () -> Assertions.assertEquals("Twant", user.getLastName()),
                () -> Assertions.assertEquals("brucet@gmail.com", user.getEmail()),
                () -> Assertions.assertEquals(LocalDate.of(1975, 12, 30), user.getDateOfBirth()),
                () -> Assertions.assertEquals("+353838547265", user.getPhone()),
                () -> Assertions.assertEquals("http://www.linledin.com/brucetwant/", user.getGitHubProfile())
        );

    }

    @Test
    void shouldThrowExceptionWhenThereWillBeConflictedIdDuringUpdate() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        User userToUpdate = new User();
        userToUpdate.setName("John");
        userToUpdate.setLastName("Miller");
        userToUpdate.setEmail("jmiller@gmail.com");
        userToUpdate.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userToUpdate.setPhone("+0000000000000");
        userToUpdate.setGitHubProfile("http://www.linkedin.com/jmiller");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userRepositoryInMemory.update(userToUpdate)
        );
    }
}
