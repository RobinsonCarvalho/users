package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.UtilPersonal;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser() {

        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");

        Assertions.assertNotNull(user);
    }

    @Test
    void shouldStorageUser() {

        UserRepository userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");
        userRepository.create(user);
        Assertions.assertEquals(1,userRepository.count());
    }

}
