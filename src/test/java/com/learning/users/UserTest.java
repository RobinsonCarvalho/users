package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.Controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserTest {

    @Test
    void shouldVerifyIfUserDataAreAccordinglyWithValidator(){

        UserController userController = new UserController();
        User user = new User();

        user.setId(1);
        user.setName("Robinson$");
        user.setLastName("Carvalho");
        user.setEmail("robinsoncarvalho@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834198168");
        user.setGitHubProfile("http://www.linkedin.com");
        userController.addUser(user);
        Assertions.assertTrue(userController.addUser(user));
    }

    @Test
    void shouldSetValuesToAttributesOfUserClass() {

        User user = new User();
        user.setId(1);
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");


        Assertions.assertAll("user",
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals("Joaquim", user.getName()),
                () -> Assertions.assertEquals("Gusmao", user.getLastName()),
                () -> Assertions.assertEquals("joaquim.gusmao@gmail.com", user.getEmail()),
                () -> Assertions.assertEquals(LocalDate.of(2000, 5, 2), user.getDateOfBirth()),
                () -> Assertions.assertEquals("+353834185473", user.getPhone()),
                () -> Assertions.assertEquals("http://www.linkedin.com", user.getGitHubProfile())
                //() -> Assertions.assertTrue(InetAddress.getByName(user.getGitHubProfile()).isReachable(3000))
        );
    }

    @Test
    void shouldGetValuesFromAttributesOfUserClass(){

        User user = new User();
        user.setId(1);
        user.setName("Garry");
        user.setLastName("O'Connor");
        user.setEmail("garryconnor@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 2));
        user.setPhone("+353834587412");
        user.setGitHubProfile("http://www.linkedin.com");

        Assertions.assertAll("user",
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals( "Garry", user.getName()),
                () -> Assertions.assertEquals( "O'Connor", user.getLastName()),
                () -> Assertions.assertEquals( "garryconnor@gmail.com", user.getEmail()),
                () -> Assertions.assertEquals( LocalDate.parse("1988-09-02"), user.getDateOfBirth()),
                () -> Assertions.assertEquals( "+353834587412", user.getPhone()),
                () -> Assertions.assertEquals( "http://www.linkedin.com", user.getGitHubProfile())

        );
    }

    @Test
    void shouldNotBeMinorAge() {

        User user = new User();

        user.setName("Peter");
        user.setLastName("O'Leary");
        user.setEmail("peter.oleary@gmail.com");
        user.setDateOfBirth(LocalDate.of(2004, 1, 8));
        user.setPhone("+353894658365");
        user.setGitHubProfile("http://www.linkedin.com");
        Assertions.assertTrue(user.getDateOfBirth().isBefore(LocalDate.now().minusYears(18)));
        Assertions.assertFalse(LocalDate.now().isBefore(LocalDate.now().minusYears(18)));
    }

}
