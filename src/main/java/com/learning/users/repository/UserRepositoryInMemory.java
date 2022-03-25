package com.learning.users.repository;

import com.learning.users.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryInMemory implements UserRepository{

    jakarta.validation.Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    Map<String, User> mapStorage = new HashMap<>();

    @Override
    public void create(User user){

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }else if(mapStorage.containsKey(user.getEmail())){
            throw new KeyAlreadyExistsException(user.getEmail());
        }

        int idUser = mapStorage.size() + 1;
        user.setId(idUser);
        mapStorage.put(user.getEmail(), user);
        System.out.println("User created successfully.");
    }

    @Override
    public void update(User user){

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        if(user.getId() == mapStorage.get(user.getEmail()).getId()) {

            User userUpd = mapStorage.get(user.getEmail());
            userUpd.setUpdatedAt(LocalDateTime.now());
            mapStorage.replace(userUpd.getEmail(), user, userUpd);
            System.out.println("User data updated successfully.");

        }
        else{

            throw new IllegalArgumentException();
        }

    }

    @Override
    public void delete(User user){

        if(!mapStorage.containsKey(user.getEmail())) {
            throw new InvalidKeyException(user.getEmail());
        }
        else if(user.getId() != mapStorage.get(user.getEmail()).getId()){
            throw new IllegalArgumentException();
        }
        else if(mapStorage.get(user.getEmail()).getDeletedAt() != null){
            throw new IllegalArgumentException();
        }

        user.setDeletedAt(LocalDateTime.now());
        mapStorage.replace(user.getEmail(), user);
        System.out.println("User " + user.getEmail().toUpperCase() + " was deleted successfully.");

    }

    @Override
    public User read(String email){

        if(!mapStorage.containsKey(email)
                || mapStorage.get(email).getDeletedAt() != null){

            throw new InvalidKeyException(email);
        }

        return mapStorage.get(email);

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
                listOfUser = mapStorage.values().stream()
                        .collect(Collectors.toList());
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

}
