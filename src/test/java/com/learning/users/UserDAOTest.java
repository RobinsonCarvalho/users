package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.model.UserDAO;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserDAOTest {

    User user;
    UserDAO userDAO;

    @Test
    void shouldInsertUser() {

        user = new User();
        userDAO = new UserDAO();
        user.setName("Reynold");
        user.setLastName("O'Sean");
        user.setEmail("reynold.oshean.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");

        try {
            userDAO.create(user);
            Assertions.assertNotNull(userDAO.read(user.getId()));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void shouldReadUserData() {

        userDAO = new UserDAO();
        user = new User();

        try {
            Assertions.assertNotNull(userDAO.read("reynold.oshean.gusmao@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldUpdateUserData() {

        userDAO = new UserDAO();
        user = new User();
        int idUser;

        try {
            user = userDAO.read("reynold.oshean.gusmao@gmail.com");

            idUser = user.getId();
            user.setName(user.getName().concat("Complemented"));
            userDAO.update(user);

            Assertions.assertEquals("ReynoldComplemented", userDAO.read(idUser).getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void shouldDeleteUserData() {

        userDAO = new UserDAO();
        user = new User();

        try {
            user = userDAO.read("reynold.oshean.gusmao@gmail.com");
            userDAO.delete(user);
            Assertions.assertNotNull(userDAO.read(user.getEmail()).getDeletedAt());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
