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
    void shouldCreateUser(){
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
    void shouldNotCreateUser(){
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
    void shouldNotReplicateUser(){
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
                ()-> userRepositoryInMemory.create(newUser)
        );
    }

    @Test
    void shouldUpdateDataFromTheUser(){
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
    void shouldNotUpdateUserWhenFieldValueIsMissing(){
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
                ()-> userRepositoryInMemory.update(user)
        );
    }

    @Test
    void shouldWarnWhenUserNotFound(){
        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Mary");
        user.setLastName("Gordon");
        user.setEmail("emailinexistent@test.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178826");
        user.setGitHubProfile("http://www.linkedin.com/marygordon");

        Assertions.assertThrows(InvalidKeyException.class,
                ()-> userRepositoryInMemory.read(user.getEmail())
        );
    }

    @Test
    void shouldDeleteUserRequested(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User firstUser = new User();
        firstUser.setName("John");
        firstUser.setLastName("Miller");
        firstUser.setEmail("jmiller@gmail.com");
        firstUser.setDateOfBirth(LocalDate.of(1955, 7, 30));
        firstUser.setPhone("+353834178265");
        firstUser.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(firstUser);

        User secondUser = new User();
        secondUser.setName("Giuseppe");
        secondUser.setLastName("Batistini");
        secondUser.setEmail("c.batistini@gmail.com");
        secondUser.setDateOfBirth(LocalDate.of(2000, 5, 2));
        secondUser.setPhone("+353834185473");
        secondUser.setGitHubProfile("http://www.linkedin.com/batistini");
        userRepositoryInMemory.create(secondUser);

        userRepositoryInMemory.delete(firstUser.getEmail());

        Assertions.assertThrows(InvalidKeyException.class,
                () -> userRepositoryInMemory.read(firstUser.getEmail())
        );

    }

}
