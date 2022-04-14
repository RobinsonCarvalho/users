package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.model.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest(classes = UserController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest  {

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;

    @Autowired
    final TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void shouldEnsureApplicationIsRunning() {

        Assertions.assertNotNull(userController);
    }
    
    @Test
    void shouldCertifyInclusionOfUserThroughRestAPI() {

        User user = new User();
        user.setName("Reynold");
        user.setLastName("O'Shean");
        user.setEmail("reynoldosheangusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");

        Assertions.assertEquals(200,
                this.testRestTemplate.postForEntity("http://localhost:" + this.port + "/user/add",
                        user, User.class)
                        .getStatusCodeValue());
    }

    @Test
    void shouldUpdateUserThroughRestAPIRequest(){

        User user = new User();
        user.setName("Reynold");
        user.setLastName("O'Shean");
        user.setEmail("reynold.oshean.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");

        Assertions.assertEquals(200,  this.testRestTemplate
                .postForEntity("http://localhost:" + this.port + "/user/add", user, User.class).getStatusCodeValue());

        User userUpd = new User();
        userUpd.setName("Reynold");
        userUpd.setLastName("O'Shean");
        userUpd.setEmail("reynoldoshean@gmail.com");
        userUpd.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userUpd.setPhone("+353000000000");
        userUpd.setGitHubProfile("http://www.linkedin.com/reynoldoshean");

        this.testRestTemplate.put("http://localhost:" + this.port + "/user/1", userUpd);

    }

}
