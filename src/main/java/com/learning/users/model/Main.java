package com.learning.users.model;

import com.learning.users.repository.UserRepositoryInMemory;
import utility.UtilPersonal;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choice;
        Scanner scanner = new Scanner(System.in);
        UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        try {
            do {
                System.out.println("CHOOSE AN ACTION TO BE EXECUTED.\n");
                System.out.println("0 - EXIT" +
                        "\n1 - CREATE"+
                        "\n2 - READ" +
                        "\n3 - UPDATE" +
                        "\n4 - DELETE\n");

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
                        System.out.println("\nREADING - INFORM USER ID: ");
                        User userRead;
                        userRead = userRepositoryInMemory.read(scanner.nextInt());
                        displayUser(userRead);
//                        System.out.println(userRead.getId() + " | "
//                            + userRead.getFirstName() + " | "
//                            + userRead.getLastName() + " | "
//                            + userRead.getBirthDay() + " | "
//                            + userRead.getEmail() + " | "
//                            + userRead.getGender() + " | "
//                            + userRead.getMaritalStatus() + " | "
//                            + userRead.getIdPartner());
                        break;

                    case 3:
                        System.out.println("\nUPDATING - INFORM USER ID: ");
                        User userUpd;
                        userUpd = userRepositoryInMemory.read(scanner.nextInt());

                        Field[] field = userUpd.getClass().getDeclaredFields();
                        for(Field f : field){
                            System.out.println(f.getName());
                        }
                        userRepositoryInMemory.update(userUpd);
                        break;

                    case 4:
                        System.out.println("\nDELETING - INFORM USER ID: ");
                        userRepositoryInMemory.delete(scanner.nextInt());

                        break;
                }
            }while (choice != 0) ;
        }
        catch(IllegalArgumentException e){
            System.out.println("Option informed are not among expected." + e.getMessage());
        }
    }

    private static List<User> loadUser(){

        UtilPersonal utilPersonal = new UtilPersonal();
        List<User> user = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Robinson");
        user1.setLastName("Carvalho");
        user1.setBirthDay(utilPersonal.formattingDate("19-3-1983"));
        user1.setEmail("robinsonpc@hotmail.com");
        user1.setGender(User.Gender.MALE);
        user1.setMaritalStatus(User.MaritalStatus.MARRIED);
        user1.setIdPartner(2);
        user.add(user1);

        User user2 = new User();
        user2.setFirstName("Bruna");
        user2.setLastName("Vidal");
        user2.setBirthDay(utilPersonal.formattingDate("29-02-1988"));
        user2.setEmail("brunneca@bol.com.br");
        user2.setGender(User.Gender.FEMALE);
        user2.setMaritalStatus(User.MaritalStatus.MARRIED);
        user2.setIdPartner(1);
        user.add(user2);

        User user3 = new User();
        user3.setFirstName("Roney");
        user3.setLastName("Pires Carvalho");
        user3.setBirthDay(utilPersonal.formattingDate("07-04-1987"));
        user3.setEmail("roneyrpc@yahoo.com.br");
        user3.setGender(User.Gender.MALE);
        user3.setMaritalStatus(User.MaritalStatus.MARRIED);
        user3.setIdPartner(4);
        user.add(user3);

        User user4 = new User();
        user4.setFirstName("Jaine");
        user4.setLastName("Silva");
        user4.setBirthDay(utilPersonal.formattingDate("15-05-1992"));
        user4.setEmail("jaine@bol.com.br");
        user4.setGender(User.Gender.FEMALE);
        user4.setMaritalStatus(User.MaritalStatus.MARRIED);
        user4.setIdPartner(3);
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