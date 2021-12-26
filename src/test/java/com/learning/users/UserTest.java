package com.learning.users;

import com.learning.users.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.UtilPersonal;

import java.util.Calendar;

public class UserTest {

    @Test
    void shouldSetValuesToAttributesOfUserClass() {

        User user = new User();
        user.setId(1);
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");

        Assertions.assertAll("user",
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals("Joaquim", user.getName()),
                () -> Assertions.assertEquals("Gusmao", user.getLastName()),
                () -> Assertions.assertEquals("joaquim.gusmao@gmail.com", user.getEmail()),
                () -> Assertions.assertEquals(UtilPersonal.formattingDate("02-05-2000"), user.getDateOfBirth()),
                () -> Assertions.assertEquals(834185473, user.getPhone()),
                () -> Assertions.assertEquals("Inserting data to simulate a gitHub profile. This will be integrated in the future", user.getGitHubProfile())
        );
    }

    @Test
    void shouldGetValuesFromToAttributesOfUserClass() {

        User user = new User();
        user.setId(1);
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");

        Assertions.assertAll("user",
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertNotNull( user.getName()),
                () -> Assertions.assertNotNull( user.getLastName()),
                () -> Assertions.assertNotNull( user.getEmail()),
                () -> Assertions.assertNotNull( user.getDateOfBirth()),
                () -> Assertions.assertEquals( 834185473, user.getPhone()),
                () -> Assertions.assertNotNull( user.getGitHubProfile())
        );
    }

    @Test
    void shouldNotBeMinorAge() {

        User user = new User();

        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("15-12-2005"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");

        Calendar dateExpected = Calendar.getInstance();
        dateExpected.add(Calendar.YEAR, -18);
        dateExpected.setTime(dateExpected.getTime());

        Assertions.assertFalse(user.getDateOfBirth().before(dateExpected.getTime()));
    }

}
