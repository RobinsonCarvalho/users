package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser() {

        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("linkedin.com");

        Assertions.assertNotNull(user);
    }

    @Test
    void shouldStorageUser(){

        UserRepository userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("linkedin.com");
        userRepository.create(user);
        Assertions.assertEquals(1,userRepository.count());
    }

}
