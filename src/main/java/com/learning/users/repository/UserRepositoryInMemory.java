package com.learning.users.repository;

import com.learning.users.model.User;
import com.learning.users.model.UserNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryInMemory implements UserRepository{

    javax.validation.Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(Validation.buildDefaultValidatorFactory().getMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    Map<Integer, User> mapStorage = new HashMap<>();

    @Override
    public void create(User user){

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }else if (mapStorage.values().stream()
                .anyMatch(store -> store.getEmail().equals(user.getEmail()))){
            throw new IllegalArgumentException();
        }

        int idUser = mapStorage.size() + 1;
        user.setId(idUser);
        mapStorage.put(user.getId(), user);
        System.out.println("User created successfully.");

    }

    @Override
    public void update(User user){

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        else if(!read(user.getId()).equals(user)){
            throw new UserNotFoundException(user.getId());
        }

        User userUpd = read(user.getId());
        userUpd.setUpdatedAt(LocalDateTime.now());
        mapStorage.replace(userUpd.getId(), user, userUpd);
        System.out.println("User data updated successfully.");
    }

    @Override
    public void delete(User user){

        User userDelete = read(user.getId());

        if(!userDelete.equals(user)){
            throw new NullPointerException();
        }
        else if(userDelete.getDeletedAt() != null){
            throw new IllegalArgumentException();
        }

        user.setDeletedAt(LocalDateTime.now());
        mapStorage.replace(user.getId(), user);
        System.out.println("User " + user.getEmail() + " was deleted successfully.");
    }

    @Override
    public User read(int id){

        if(!mapStorage.containsKey(id) || mapStorage.get(id).getDeletedAt() != null){
            throw new UserNotFoundException(id);
        }
        return mapStorage.get(id);
    }

    @Override
    public User read(String email){

        User user = new User();

        if(mapStorage.size() == 0){
            throw new UserNotFoundException(email);
        }
        else if(mapStorage.values().stream().noneMatch(user1 -> user1.getEmail().equals(email))){
            throw new UserNotFoundException(user.getEmail());
        }

        return mapStorage.get(mapStorage.values().stream().anyMatch(
                usr -> usr.getEmail().equals(email)));

    }

    @Override
    public int count(){

        return mapStorage.size();
    }

    /*
    The method allows the user to find and to list users name.
    String nameForSearching: parameter Name can be partially or fully informed.
    int QuantityToList: parameter refers to the number of elements to be returned
    int maxQuantity: is the maximum number of user allowed to be shown
    defined.
     */
    @Override
    public List<User> list(boolean active, int limitToList, String name){

        if(limitToList > 50){
            throw new IllegalArgumentException("The maximum users requests must be 50");
        }

        List<User> listOfUser;

       if(!active){
            if(name.length() == 0) {
                listOfUser = new ArrayList<>(mapStorage.values());
            }
            else{
                listOfUser = mapStorage.values().stream()
                        .filter(user -> user.getName().contains(name))
                        .collect(Collectors.toList());
            }
        }
        else {
            if(name.length() > 0) {
                listOfUser = mapStorage.values().stream()
                        .filter(user -> user.getDeletedAt() == null)
                        .filter(user -> user.getName().contains(name))
                        .collect(Collectors.toList());
            }
            else{
                listOfUser = mapStorage.values().stream()
                        .filter(user -> user.getDeletedAt() == null)
                        .collect(Collectors.toList());
            }
        }

        if(listOfUser.size() > limitToList){
            throw new IllegalArgumentException();
        }
        return listOfUser;
    }

    @Override
    public boolean findUser(int idUser){

        return mapStorage.containsKey(idUser);

    }
}
