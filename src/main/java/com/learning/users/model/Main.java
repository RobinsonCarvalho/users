package com.learning.users.model;

import com.learning.users.repository.UserRepositoryInMemory;
import utility.UtilPersonal;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.util.Objects.isNull;

public class Main {

    public static void main(String[] args) {

        int choice;
        Scanner scanner = new Scanner(System.in);
        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        try {
            do {
                System.out.println("\n0 - EXIT" +
                        "\n1 - CREATE"+
                        "\n2 - READ" +
                        "\n3 - UPDATE" +
                        "\n4 - DELETE" +
                        "\nCHOOSE AN ACTION ABOVE TO BE EXECUTED: ");

                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        List<User> users;
                        users = loadUser();
                        for (User u : users) {
                            userRepositoryInMemory.create(u);
                        }
                        displayUser(users);
                        break;

                    case 2:
                        System.out.println("    READING - INFORM USER ID: ");
                        User userRead;
                        userRead = userRepositoryInMemory.read(scanner.nextInt());

                        if (isNull(userRead)) {
                            System.out.println("User is not listed.");
                        } else {
                            displayUser(userRead);
                        }
                        break;

                    case 3:
                        System.out.println("    UPDATING - INFORM USER ID: ");
                        User userUpd;
                        userUpd = userRepositoryInMemory.read(scanner.nextInt());

                        Field[] field = userUpd.getClass().getDeclaredFields();
                        for(Field f : field){
                            System.out.println(f.getName() + ": ");
                            var valueInformed = scanner.nextLine();
                            switch (f.getName()){
                                case "firstName":
                                    userUpd.setFirstName(valueInformed);
                                    break;
                                case "lastName":
                                    userUpd.setLastName(valueInformed);
                                    break;
                                case "birthDay":
                                    userUpd.setBirthDay(utility.UtilPersonal.formattingDate(valueInformed));
                                    break;
                                case "gender":
                                    userUpd.setGender(User.Gender.valueOf(valueInformed));
                                    break;
                                case "email":
                                    userUpd.setEmail(valueInformed);
                                    break;
                                case "maritalStatus":
                                    userUpd.setMaritalStatus(User.MaritalStatus.valueOf(valueInformed));
                                    break;
                                case "idPartner":
                                    userUpd.setIdPartner(scanner.nextInt());
                                    break;
                            }
                        }
                        userRepositoryInMemory.update(userUpd);
                        displayUser(userUpd);
                        break;

                    case 4:
                        System.out.println("    DELETING - INFORM USER ID: ");
                        if(userRepositoryInMemory.delete(scanner.nextInt())){
                            System.out.println("User deleted.");
                        }
                        else{
                            System.out.println("User was not found. Please check if its listed.");
                        };
                        break;
                }
            }while (choice != 0) ;

            System.out.println("Program finalize by user.");

            scanner.close();
            System.out.println("Program version: " +
                    com.learning.users.model.User.class.getPackage().getImplementationVersion());

        }
        catch(IllegalArgumentException e){
            System.out.println("Option informed are not among expected." + e.getMessage());
        }
    }

    private static List<User> loadUser(){

        UtilPersonal utilPersonal = new UtilPersonal();
        List<User> user = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Lionel");
        user1.setLastName("Messi");
        user1.setBirthDay(utilPersonal.formattingDate("10-5-1990"));
        user1.setEmail("lionelmessi@barcelona.com");
        user1.setGender(User.Gender.MALE);
        user1.setMaritalStatus(User.MaritalStatus.MARRIED);
        user1.setIdPartner(0);
        user.add(user1);

        User user2 = new User();
        user2.setFirstName("Cristiano");
        user2.setLastName("Ronaldo");
        user2.setBirthDay(utilPersonal.formattingDate("29-02-1988"));
        user2.setEmail("cristianoronaldo@juventus.com.br");
        user2.setGender(User.Gender.FEMALE);
        user2.setMaritalStatus(User.MaritalStatus.MARRIED);
        user2.setIdPartner(0);
        user.add(user2);

        User user3 = new User();
        user3.setFirstName("Neymar");
        user3.setLastName("Junior");
        user3.setBirthDay(utilPersonal.formattingDate("07-04-1995"));
        user3.setEmail("neynarjr@psg.com");
        user3.setGender(User.Gender.MALE);
        user3.setMaritalStatus(User.MaritalStatus.SINGLE);
        user3.setIdPartner(0);
        user.add(user3);

        User user4 = new User();
        user4.setFirstName("Robert");
        user4.setLastName("Lewandowski");
        user4.setBirthDay(utilPersonal.formattingDate("15-05-1992"));
        user4.setEmail("robertlewandowski@bayernmunich.com");
        user4.setGender(User.Gender.MALE);
        user4.setMaritalStatus(User.MaritalStatus.MARRIED);
        user4.setIdPartner(0);
        user.add(user4);
        return user;
    }

    private static void displayUser(List<User> user){

        for(User u : user){
            System.out.println(u.getId() + " | "
                + u.getFirstName() + " | "
                + u.getLastName() + " | "
                + u.getBirthDay() + " | "
                + u.getEmail() + " | "
                + u.getGender() + " | "
                + u.getMaritalStatus() + " | "
                + u.getIdPartner());
        }
    }

    private static void displayUser(User user){

        System.out.println(user.getId() + " | "
            + user.getFirstName() + " | "
            + user.getLastName() + " | "
            + user.getBirthDay() + " | "
            + user.getEmail() + " | "
            + user.getGender() + " | "
            + user.getMaritalStatus() + " | "
            + user.getIdPartner());
    }
}