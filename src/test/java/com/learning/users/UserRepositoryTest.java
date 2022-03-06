package com.learning.users;

import com.learning.users.model.User;
import com.learning.users.repository.UserRepositoryInMemory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class UserRepositoryTest {

    @Test
    void shouldCreateUser(){

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
    void shouldNotCreateUser(){
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
    void shouldNotReplicateUser(){
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
        Assertions.assertThrows(KeyAlreadyExistsException.class,
                ()-> userRepositoryInMemory.create(newUser)
        );
    }

    @Test
    void shouldUpdateDataFromTheUser(){
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
        newUser = userRepositoryInMemory.read(user.getEmail());
        Assertions.assertEquals("Miller Roosevelt", newUser.getLastName());
    }

    @Test
    void shouldNotUpdateUserWhenFieldValueIsMissing(){
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
                ()-> userRepositoryInMemory.update(user)
        );
    }

    @Test
    void shouldWarnWhenUserNotFound(){
        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Mary");
        user.setLastName("Gordon");
        user.setEmail("emailinexistent@test.com");
        user.setDateOfBirth(LocalDate.of(1988, 9, 15));
        user.setPhone("+353834178826");
        user.setGitHubProfile("http://www.linkedin.com/marygordon");

        Assertions.assertThrows(InvalidKeyException.class,
                ()-> userRepositoryInMemory.read(user.getEmail())
        );
    }

    @Test
    void shouldDeleteUserRequested(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User firstUser = new User();
        firstUser.setName("John");
        firstUser.setLastName("Miller");
        firstUser.setEmail("jmiller@gmail.com");
        firstUser.setDateOfBirth(LocalDate.of(1955, 7, 30));
        firstUser.setPhone("+353834178265");
        firstUser.setGitHubProfile("http://www.linkedin.com/jmiller");
        userRepositoryInMemory.create(firstUser);

        User secondUser = new User();
        secondUser.setName("Giuseppe");
        secondUser.setLastName("Batistini");
        secondUser.setEmail("c.batistini@gmail.com");
        secondUser.setDateOfBirth(LocalDate.of(2000, 5, 2));
        secondUser.setPhone("+353834185473");
        secondUser.setGitHubProfile("http://www.linkedin.com/batistini");
        userRepositoryInMemory.create(secondUser);

        userRepositoryInMemory.delete(firstUser.getEmail());

        Assertions.assertThrows(InvalidKeyException.class,
                () -> userRepositoryInMemory.read(firstUser.getEmail())
        );
    }

    @Test
    void shouldGetUserData(){
        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
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
        userF.setEmail("brucet@gmail.com");
        userF.setDateOfBirth(LocalDate.of(1975,12, 30));
        userF.setPhone("+353838547265");
        userF.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUsers.add(userF);

        for(User user : listOfUsers){
            userRepositoryInMemory.create(user);
        }

        Assertions.assertEquals(2, userRepositoryInMemory.listUser("Jo").size());
    }

    @Test
    void shouldNotSearchIfParameterDoesNotMeetTheRequirements(){

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        User user = new User();
        user.setName("Bruce");
        user.setLastName("Twant");
        user.setEmail("brucet@gmail.com");
        user.setDateOfBirth(LocalDate.of(1975,12, 30));
        user.setPhone("+353838547265");
        user.setGitHubProfile("http://www.linledin.com/brucetwant/");

        userRepositoryInMemory.create(user);

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userRepositoryInMemory.listUser(""),
            "At least two characters have to be provided");
    }

    @Test
    void shouldWarnUserOfLimitationOfUserToBeReturned() {

        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();
        List<User> listOfUser = new ArrayList<>();

        User userA = new User();
        userA.setName("Reynold");
        userA.setLastName("O'Shean");
        userA.setEmail("reynold.oshean@gmail.com");
        userA.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userA.setPhone("+353834185473");
        userA.setGitHubProfile("http://www.linkedin.com");
        listOfUser.add(userA);

        User userB = new User();
        userB.setName("Giuseppe");
        userB.setLastName("Batistini");
        userB.setEmail("c.batistini@gmail.com");
        userB.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userB.setPhone("+353834185473");
        userB.setGitHubProfile("http://www.linkedin.com/batistini");
        listOfUser.add(userB);

        User userC = new User();
        userC.setName("John");
        userC.setLastName("Miller");
        userC.setEmail("mellanie@gmail.com");
        userC.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userC.setPhone("+353834178265");
        userC.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUser.add(userC);

        User userD = new User();
        userD.setName("Joseph");
        userD.setLastName("Stuart");
        userD.setEmail("bernard@gmail.com");
        userD.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userD.setPhone("+353834754577");
        userD.setGitHubProfile("http://www.linkedin.com/joseph");
        listOfUser.add(userD);

        User userE = new User();
        userE.setName("Mary");
        userE.setLastName("Gordon");
        userE.setEmail("bryan@test.com");
        userE.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userE.setPhone("+353834178826");
        userE.setGitHubProfile("http://www.linkedin.com/marygordon");
        listOfUser.add(userE);

        User userF = new User();
        userF.setName("Bruce");
        userF.setLastName("Twant");
        userF.setEmail("brucet@gmail.com");
        userF.setDateOfBirth(LocalDate.of(1975,12, 30));
        userF.setPhone("+353838547265");
        userF.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userF);

        User userG = new User();
        userG.setName("Reynold");
        userG.setLastName("O'Shean");
        userG.setEmail("reynold.oshean.gusmao@gmail.com");
        userG.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userG.setPhone("+353834185473");
        userG.setGitHubProfile("http://www.linkedin.com");
        listOfUser.add(userG);

        User userH = new User();
        userH.setName("Giuseppe");
        userH.setLastName("Batistini");
        userH.setEmail("jeniffer@gmail.com");
        userH.setDateOfBirth(LocalDate.of(2000, 5, 2));
        userH.setPhone("+353834185473");
        userH.setGitHubProfile("http://www.linkedin.com/batistini");
        listOfUser.add(userH);

        User userI = new User();
        userI.setName("John");
        userI.setLastName("Miller");
        userI.setEmail("jmiller@gmail.com");
        userI.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userI.setPhone("+353834178265");
        userI.setGitHubProfile("http://www.linkedin.com/jmiller");
        listOfUser.add(userI);

        User userJ = new User();
        userJ.setName("Joseph");
        userJ.setLastName("Stuart");
        userJ.setEmail("josephstuart@gmail.com");
        userJ.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userJ.setPhone("+353834754577");
        userJ.setGitHubProfile("http://www.linkedin.com/joseph");
        listOfUser.add(userJ);

        User userK = new User();
        userK.setName("Mary");
        userK.setLastName("Gordon");
        userK.setEmail("emailinexistent@test.com");
        userK.setDateOfBirth(LocalDate.of(1988, 9, 15));
        userK.setPhone("+353834178826");
        userK.setGitHubProfile("http://www.linkedin.com/marygordon");
        listOfUser.add(userK);

        User userL = new User();
        userL.setName("Bruce");
        userL.setLastName("Twant");
        userL.setEmail("anthony@gmail.com");
        userL.setDateOfBirth(LocalDate.of(1975,12, 30));
        userL.setPhone("+353838547265");
        userL.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userL);

        User userM = new User();
        userM.setName("Bruce");
        userM.setLastName("Twant");
        userM.setEmail("george@gmail.com");
        userM.setDateOfBirth(LocalDate.of(1975,12, 30));
        userM.setPhone("+353838547265");
        userM.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userM);

        User userN = new User();
        userN.setName("Bruce");
        userN.setLastName("Twant");
        userN.setEmail("michael@gmail.com");
        userN.setDateOfBirth(LocalDate.of(1975,12, 30));
        userN.setPhone("+353838547265");
        userN.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userN);

        User userO = new User();
        userO.setName("Bruce");
        userO.setLastName("Twant");
        userO.setEmail("kevyn@gmail.com");
        userO.setDateOfBirth(LocalDate.of(1975,12, 30));
        userO.setPhone("+353838547265");
        userO.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userO);

        User userP = new User();
        userP.setName("Bruce");
        userP.setLastName("Twant");
        userP.setEmail("dwayne@gmail.com");
        userP.setDateOfBirth(LocalDate.of(1975,12, 30));
        userP.setPhone("+353838547265");
        userP.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userP);

        User userQ = new User();
        userQ.setName("Bruce");
        userQ.setLastName("Twant");
        userQ.setEmail("harry@gmail.com");
        userQ.setDateOfBirth(LocalDate.of(1975,12, 30));
        userQ.setPhone("+353838547265");
        userQ.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userQ);

        User userR = new User();
        userR.setName("Bruce");
        userR.setLastName("Twant");
        userR.setEmail("rebecca@gmail.com");
        userR.setDateOfBirth(LocalDate.of(1975,12, 30));
        userR.setPhone("+353838547265");
        userR.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userR);

        User userS = new User();
        userS.setName("Bruce");
        userS.setLastName("Twant");
        userS.setEmail("eamon@gmail.com");
        userS.setDateOfBirth(LocalDate.of(1975,12, 30));
        userS.setPhone("+353838547265");
        userS.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userS);

        User userT = new User();
        userT.setName("Bruce");
        userT.setLastName("Twant");
        userT.setEmail("lorraine@gmail.com");
        userT.setDateOfBirth(LocalDate.of(1975,12, 30));
        userT.setPhone("+353838547265");
        userT.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userT);

        User userU = new User();
        userU.setName("Bruce");
        userU.setLastName("Twant");
        userU.setEmail("terrence@gmail.com");
        userU.setDateOfBirth(LocalDate.of(1975,12, 30));
        userU.setPhone("+353838547265");
        userU.setGitHubProfile("http://www.linledin.com/brucetwant/");
        listOfUser.add(userU);

        for(User user : listOfUser){
            userRepositoryInMemory.create(user);
        }

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userRepositoryInMemory.listUser("Bru"),
                "");
    }
}
