package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Reynold");
        user.setLastName("O'Shean");
        user.setEmail("reynold.oshean.gusmao@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com");
        userRepositoryInMemory.create(user);
        Assertions.assertEquals(1, userRepositoryInMemory.count());
    }

    @Test
    void shouldNotCreateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("");
        user.setLastName("Batistini");
        user.setEmail("c.batistini@gmail.com");
        user.setDateOfBirth(LocalDate.of(2000, 5, 2));
        user.setPhone("+353834185473");
        user.setGitHubProfile("http://www.linkedin.com/batistini");
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> userRepositoryInMemory.create(user)
        );

    }

    @Test
    void shouldNotReplicateUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        User newUser = new User();
        newUser.setName("Roy");
        newUser.setLastName("Stuart");
        newUser.setEmail("jmiller@gmail.com");
        newUser.setDateOfBirth(LocalDate.of(1988, 9, 15));
        newUser.setPhone("+353834754577");
        newUser.setGitHubProfile("http://www.linkedin.com/roy");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userRepositoryInMemory.create(newUser)
        );

    }

    @Test
    void shouldUpdateDataFromTheUser() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();

        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        user.setLastName("Miller Roosevelt");
        userRepositoryInMemory.update(user);

        User newUser;
        newUser = userRepositoryInMemory.read(user.getId());
        Assertions.assertEquals("Miller Roosevelt", newUser.getLastName());

    }

    @Test
    void shouldNotUpdateUserWhenFieldValueIsMissing() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);
        user.setLastName("");
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> userRepositoryInMemory.update(user)
        );

    }

    @Test
    void shouldWarnWhenUserNotFound() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Mary");
        user.setLastName("Gordon");
        user.setEmail("emailinexistent@test.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178826");
        user.setGitHubProfile("http://www.linkedin.com/marygordon");

        Assertions.assertThrows(InvalidKeyException.class,
                () -> userRepositoryInMemory.read(user.getId())
        );
    }

    @Test
    void shouldDeleteUserRequested(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        userRepositoryInMemory.delete(user);

        Assertions.assertThrows(InvalidKeyException.class,
                        () -> userRepositoryInMemory.read(user.getId())
        );

    }

    @Test
    void shouldThrowExceptionWhenUserForDeletionNotFound(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        Assertions.assertThrows(IllegalArgumentException.class,
                        () -> {
                            user.setId(2);
                            userRepositoryInMemory.delete(user);
                        }
        );

    }

    @Test
    void shouldThrowExceptionWhenIdUserDiffersFromStoredOne(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        Assertions.assertThrows(IllegalArgumentException.class,
                () ->{
                    User userCheck = new User();
                    userCheck.setId(22);
                    userCheck.setEmail("jmiller@gmail.com");
                    userRepositoryInMemory.delete(userCheck);
                }
        );

    }

    @Test
    void shouldThrowIllegalExceptionWhenUserNotActive(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1955, 7, 30));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        user.setDeletedAt(LocalDateTime.now());
        userRepositoryInMemory.create(user);

        Assertions.assertThrows(IllegalArgumentException.class,
                () ->{
                    userRepositoryInMemory.delete(user);
                }
        );

    }

    @Test
    void shouldGetUserData() {

        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Bruce");
        user.setLastName("Twant");
        user.setEmail("brucetwant@gmail.com");
        user.setDateOfBirth(LocalDate.of(1975, 12, 30));
        user.setPhone("+353838547265");
        user.setGitHubProfile("http://www.linledin.com/brucetwant/");
        userRepository.create(user);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals("Bruce", user.getName()),
                () -> Assertions.assertEquals("Twant", user.getLastName()),
                () -> Assertions.assertEquals("brucetwant@gmail.com", user.getEmail()),
                () -> Assertions.assertEquals(LocalDate.of(1975, 12, 30), user.getDateOfBirth()),
                () -> Assertions.assertEquals("+353838547265", user.getPhone()),
                () -> Assertions.assertEquals("http://www.linledin.com/brucetwant/", user.getGitHubProfile())
        );

    }

    @Test
    void shouldThrowExceptionWhenBeingConflictedIdDuringUpdate() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        User user = new User();
        user.setName("John");
        user.setLastName("Miller");
        user.setEmail("jmiller@gmail.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178265");
        user.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(user);

        User userToUpdate = new User();
        userToUpdate.setId(100);
        userToUpdate.setName("John");
        userToUpdate.setLastName("Miller");
        userToUpdate.setEmail("jmiller@gmail.com");
        userToUpdate.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userToUpdate.setPhone("+0000000000000");
        userToUpdate.setGitHubProfile("http://www.linkedin.com/jmiller");

        Assertions.assertThrows(NullPointerException.class,
                () -> userRepositoryInMemory.update(userToUpdate)
        );
    }

    @Test
    void shouldListUserAsRequested(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        List<User> listOfUsers = new ArrayList<>();

        User userA = new User();
        userA.setName("Reynold");
        userA.setLastName("O'Shean");
        userA.setEmail("reynold.oshean.gusmao@gmail.com");
        userA.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userA.setPhone("+353834185473");
        userA.setGitHubProfile("http://www.linkedin.com");
        listOfUsers.add(userA);

        User userB = new User();
        userB.setName("Giuseppe");
        userB.setLastName("Batistini");
        userB.setEmail("c.batistini@gmail.com");
        userB.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userB.setPhone("+353834185473");
        userB.setGitHubProfile("http://www.linkedin.com/batistini");
        listOfUsers.add(userB);
        
        User userC = new User();
        userC.setName("John");
        userC.setLastName("Miller");
        userC.setEmail("jmiller@gmail.com");
        userC.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userC.setPhone("+353834178265");
        userC.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUsers.add(userC);

        User userD = new User();
        userD.setName("Joseph");
        userD.setLastName("Stuart");
        userD.setEmail("josephstuart@gmail.com");
        userD.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userD.setPhone("+353834754577");
        userD.setGitHubProfile("http://www.linkedin.com/joseph");
        listOfUsers.add(userD);

        User userE = new User();
        userE.setName("Mary");
        userE.setLastName("Gordon");
        userE.setEmail("emailinexistent@test.com");
        userE.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userE.setPhone("+353834178826");
        userE.setGitHubProfile("http://www.linkedin.com/marygordon");
        listOfUsers.add(userE);
        
        User userF = new User();
        userF.setName("Bruce");
        userF.setLastName("Twant");
        userF.setEmail("brucetwant@gmail.com");
        userF.setDateOfBirth(LocalDate.of(1975,12, 30));
        userF.setPhone("+353838547265");
        userF.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUsers.add(userF);

        for(User user : listOfUsers){
            userRepositoryInMemory.create(user);
        }

        Assertions.assertEquals(2, userRepositoryInMemory.list(false, 10, "Jo").size());
    }

    @Test
    void shouldReturnUserAsParameterized(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        List<User> listOfUser = new ArrayList<>();

        User userJ1 = new User();
        userJ1.setName("John");
        userJ1.setLastName("Miller");
        userJ1.setEmail("mellanie@gmail.com");
        userJ1.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ1.setPhone("+353834178265");
        userJ1.setGitHubProfile("http://www.linkedin.com/jmiller");
        userJ1.setDeletedAt(LocalDateTime.now());
        listOfUser.add(userJ1);

        User userJ2 = new User();
        userJ2.setName("Joseph");
        userJ2.setLastName("Stuart");
        userJ2.setEmail("bernard@gmail.com");
        userJ2.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ2.setPhone("+353834754577");
        userJ2.setGitHubProfile("http://www.linkedin.com/joseph");
        listOfUser.add(userJ2);

        User userJ3 = new User();
        userJ3.setName("John");
        userJ3.setLastName("Miller");
        userJ3.setEmail("jmiller@gmail.com");
        userJ3.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ3.setPhone("+353834178265");
        userJ3.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUser.add(userJ3);

        User userB1 = new User();
        userB1.setName("Bruna");
        userB1.setLastName("Teixeira");
        userB1.setEmail("brucetwant@gmail.com");
        userB1.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB1.setPhone("+353838547265");
        userB1.setGitHubProfile("http://www.linledin.com/brucetwant/");
        userB1.setDeletedAt(LocalDateTime.now());
        listOfUser.add(userB1);

        User userB2 = new User();
        userB2.setName("Bruce");
        userB2.setLastName("Twant");
        userB2.setEmail("anthony@gmail.com");
        userB2.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB2.setPhone("+353838547265");
        userB2.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userB2);

        User userB3 = new User();
        userB3.setName("Bruin");
        userB3.setLastName("Scarfir");
        userB3.setEmail("george@gmail.com");
        userB3.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB3.setPhone("+353838547265");
        userB3.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userB3);

        for(User user : listOfUser){
            userRepositoryInMemory.create(user);
        }

        Assertions.assertAll(

                () -> Assertions
                        .assertEquals(6, userRepositoryInMemory
                                .list(false,10,"").size()),

                () -> Assertions.assertEquals(4, userRepositoryInMemory
                                .list(true,50,"").size()),

                () -> Assertions.assertTrue(userRepositoryInMemory
                        .list(false,4,"NoReturn").isEmpty()),

                () -> Assertions.assertFalse(userRepositoryInMemory
                        .list(true,50,"Joseph").isEmpty())

        );
    }

    @Test
    void shouldWarnUserOfLimitationOfUserToBeReturned() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        List<User> listOfUser = new ArrayList<>();

        User userJ1 = new User();
        userJ1.setName("John");
        userJ1.setLastName("Miller");
        userJ1.setEmail("mellanie@gmail.com");
        userJ1.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ1.setPhone("+353834178265");
        userJ1.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUser.add(userJ1);

        User userJ2 = new User();
        userJ2.setName("Joseph");
        userJ2.setLastName("Stuart");
        userJ2.setEmail("bernard@gmail.com");
        userJ2.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ2.setPhone("+353834754577");
        userJ2.setGitHubProfile("http://www.linkedin.com/joseph");
        listOfUser.add(userJ2);

        User userJ3 = new User();
        userJ3.setName("John");
        userJ3.setLastName("Miller");
        userJ3.setEmail("jmiller@gmail.com");
        userJ3.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ3.setPhone("+353834178265");
        userJ3.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUser.add(userJ3);
        
        User userB1 = new User();
        userB1.setName("Bruna");
        userB1.setLastName("Teixeira");
        userB1.setEmail("brucetwant@gmail.com");
        userB1.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB1.setPhone("+353838547265");
        userB1.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userB1);
        
        User userB2 = new User();
        userB2.setName("Bruce");
        userB2.setLastName("Twant");
        userB2.setEmail("anthony@gmail.com");
        userB2.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB2.setPhone("+353838547265");
        userB2.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userB2);

        User userB3 = new User();
        userB3.setName("Bruin");
        userB3.setLastName("Scarfir");
        userB3.setEmail("george@gmail.com");
        userB3.setDateOfBirth(LocalDate.of(1975,12, 30));
        userB3.setPhone("+353838547265");
        userB3.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userB3);

        for(User user : listOfUser){
            userRepositoryInMemory.create(user);
        }

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userRepositoryInMemory
                        .list(false,
                                2,
                                "Bru"
                )
        );

    }

    @Test
    void shouldThrowExceptionDueToQuantityRequestedExceedMaximumParameterized() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userRepositoryInMemory
                        .list(false,
                                51,
                                "Bru"
                )
        );
    }

}
