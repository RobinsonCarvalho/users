package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepository;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.UtilPersonal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepositoryTest {

    @Test
    void check_methods_sets_from_user_class(){

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
                () ->Assertions.assertEquals("Gusmao", user.getLastName()),
                () ->Assertions.assertEquals("joaquim.gusmao@gmail.com", user.getEmail()),
                () ->Assertions.assertEquals(UtilPersonal.formattingDate("02-05-2000"), user.getDateOfBirth()),
                () ->Assertions.assertEquals(834185473, user.getPhone()),
                () ->Assertions.assertEquals("Inserting data to simulate a gitHub profile. This will be integrated in the future", user.getGitHubProfile())
        );
    }

    @Test
    void check_methods_gets_from_user_class(){

        User user = new User();
        user.setId(1);
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");

        Assertions.assertAll("user",
                () -> Assertions.assertNotNull(user.getId()),
                () -> Assertions.assertNotNull(user.getName()),
                () ->Assertions.assertNotNull(user.getLastName()),
                () ->Assertions.assertNotNull(user.getEmail()),
                () ->Assertions.assertNotNull(user.getDateOfBirth()),
                () ->Assertions.assertNotNull(user.getPhone()),
                () ->Assertions.assertNotNull(user.getGitHubProfile())
        );
    }

    @Test
    void should_create_user(){

        UserRepository userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("02-05-2000"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");
        userRepository.create(user);

        Assertions.assertEquals(1, userRepository.count());
    }

    @Test
    void should_not_be_minor_age(){

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

    @Test
    void createEmailFilled(){

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("15-12-2005"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");
        Matcher matcher = pattern.matcher(user.getEmail());

        Assertions.assertTrue(matcher.matches());
    }

    @Test
    void createTest(){

        Map<Integer, User> map = new HashMap<>();
        User user = new User();
        user.setName("Joaquim");
        user.setLastName("Gusmao");
        user.setEmail("joaquim.gusmao@gmail.com");
        user.setDateOfBirth(UtilPersonal.formattingDate("15-12-2005"));
        user.setPhone(834185473);
        user.setGitHubProfile("Inserting data to simulate a gitHub profile. This will be integrated in the future");
        map.put(1, user);
        Assertions.assertFalse(map.isEmpty());
    }

}
