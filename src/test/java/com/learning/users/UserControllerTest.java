package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.model.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDate;

@SpringBootTest(classes = UserController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest  {

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldEnsureApplicationIsRunning(){
        Assertions.assertNotNull(userController);
    }
    
//    @Test
//    void shouldCertifyInclusionOfUserThroughRestAPI() throws Exception {
//
//        User user = new User();
//        user.setName("Reynold");
//        user.setLastName("OShean");
//        user.setEmail("reynold.oshean.gusmao@gmail.com");
//        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
//        user.setPhone("+353834185473");
//        user.setGitHubProfile("http://www.linkedin.com");
//
//        Assertions.assertNotNull(
//                this.testRestTemplate.postForObject("http://localhost:"+ port + "/user/add/",
//                        user,
//                        User.class)
//        );
//    }
}
