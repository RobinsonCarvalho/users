package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepositoryInMemory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
                () -> {
                    userRepositoryInMemory.create(user);
                }
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
}
