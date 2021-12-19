package com.learning.users.model;

import com.learning.users.repository.UserRepositoryInMemory;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nStarting......");

        int option;

        try {
            do {
                System.out.println("\nSELECT YOUR OPTION:\n" +
                        "\t0 - Finalize Program\n" +
                        "\t1 - Add User\n" +
                        "\t2 - Display User Data\n" +
                        "\t3 - Update User Data\n" +
                        "\t4 - Delete User\n");

                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("---------------------Adding User---------------------");

                        User userAdd = new User();
                        Field[] field = userAdd.getClass().getDeclaredFields();
                        for (Field value : field) {

                            String currentProperty = value.getName();
                            System.out.println("User " + currentProperty);
                            String valueInformed = scanner.nextLine();

                            switch (currentProperty) {
                                case "name":
                                    userAdd.setName(valueInformed);
                                    break;
                                case "lastName":
                                    userAdd.setLastName(valueInformed);
                                    break;
                                case "email":
                                    userAdd.setEmail(valueInformed);
                                    break;
                                case "dateOfBirth":
                                    userAdd.setDateOfBirth(utility.UtilPersonal.formattingDate(valueInformed));
                                    break;
                                case "phone":
                                    userAdd.setPhone(Integer.parseInt(valueInformed));
                                    break;
                                case "gitHubProfile":
                                    userAdd.setGitHubProfile(valueInformed);
                                    break;
                            }
                        }

                        UserRepositoryInMemory bdInMemory = new UserRepositoryInMemory();
                        bdInMemory.create(userAdd);

                        System.out.println("-----------------------------------------------------");
                        break;
                    case 2:
                        System.out.println("2");
                        break;
                    case 3:
                        System.out.println("3");
                        break;
                    case 4:
                        System.out.println("4");
                        break;
                }

            } while (option > 0 && option < 5);
            System.out.println("Program finalize by user.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
