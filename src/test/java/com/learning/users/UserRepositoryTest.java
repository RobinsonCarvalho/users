package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser(){
        UserRepository userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(1975,12, 30));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com/jgusmao/");
        userRepository.create(user);
        Assertions.assertEquals(1, userRepository.count());
    }

    @Test
    void shouldGetUserData(){
        UserRepository userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Bruce");
        user.setLastName("Twant");
        user.setEmail("brucet@gmail.com");
        user.setDateOfBirth(LocalDate.of(1975,12, 30));
        user.setPhone("+353838547265");
        user.setGitHubProfile("http://www.linledin.com/brucetwant/");
        userRepository.create(user);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, user.getId()),
                ()-> Assertions.assertEquals("Bruce", user.getName()),
                ()-> Assertions.assertEquals("Twant", user.getLastName()),
                ()-> Assertions.assertEquals("brucet@gmail.com", user.getEmail()),
                ()-> Assertions.assertEquals(LocalDate.of(1975,12, 30), user.getDateOfBirth()),
                ()-> Assertions.assertEquals("+353838547265", user.getPhone()),
                ()-> Assertions.assertEquals("http://www.linledin.com/brucetwant/", user.getGitHubProfile())
        );
    }
}
