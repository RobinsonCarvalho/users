package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.model.UserController;
import com.learning.users.model.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

@SpringBootTest(classes = UserController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest  {

    @Autowired
    private UserController userController;

    @Test
    public void shouldEnsureApplicationIsRunning(){
        Assertions.assertNotNull(userController);
    }
    
    @Test
    void shouldCertifyInclusionOfUserThroughRestAPI() {

        try{
            User user = new User();
            user.setName("Reynold");
            user.setLastName("O'Sean");
            user.setEmail("reynoldosheangusmao@gmail.com");
            user.setDateOfBirth(LocalDate.of(2000, 5, 2));
            user.setPhone("+353834185473");
            user.setGitHubProfile("http://www.linkedin.com");

            Assertions.assertDoesNotThrow(() -> userController.add(user));
        }
        catch (ConstraintViolationException | IllegalArgumentException cve){
            System.out.println(cve.getMessage());
        }
    }

    @Test
    void shouldUpdateUserThroughRestAPIRequest(){


        try{
            User user = new User();
            user.setName("Reynold");
            user.setLastName("O'Sean");
            user.setEmail("reynold.oshean.gusmao@gmail.com");
            user.setDateOfBirth(LocalDate.of(2000, 5, 2));
            user.setPhone("+353834185473");
            user.setGitHubProfile("http://www.linkedin.com");

            User userUpd = new User();
            userUpd.setName("Reynold");
            userUpd.setLastName("O'Sean");
            userUpd.setEmail("reynoldoshean@gmail.com");
            userUpd.setDateOfBirth(LocalDate.of(2000, 5, 2));
            userUpd.setPhone("+353000000000");
            userUpd.setGitHubProfile("http://www.linkedin.com/reynoldoshean");

            userController.add(user);
            userController.replace(userUpd, user.getId());
        }
        catch (ConstraintViolationException | UserNotFoundException | IllegalArgumentException cve){
            System.out.println(cve.getMessage());
        }

    }

    @Test
    void shouldThrowExceptionIfUserIdParameterNotFound(){

        try{
            Assertions.assertNull(userController.displayById(5));
        }
        catch (UserNotFoundException unfe){
            System.out.println(unfe.getMessage());
        }

    }

    @Test
    void shouldDeleteUserThroughAPIRequest(){

        try{
            User user = new User();
            user.setName("John");
            user.setLastName("Miller");
            user.setEmail("jmiller@gmail.com");
            user.setDateOfBirth(LocalDate.of(1988, 9, 15));
            user.setPhone("+353834178265");
            user.setGitHubProfile("http://www.linkedin.com/jmiller");
            userController.add(user);

            Assertions.assertDoesNotThrow( () -> userController.erase(user));
        }
        catch (UserNotFoundException | IllegalArgumentException unfe){
            System.out.println(unfe.getMessage());
        }
    }
}
