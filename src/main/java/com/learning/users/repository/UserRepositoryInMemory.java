package com.learning.users.repository;

import com.learning.users.model.User;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.management.openmbean.InvalidKeyException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        }else if(mapStorage.containsKey(user.getId())){
            throw new IllegalArgumentException();
        }else if(mapStorage.values().stream()
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

        if(user.getId() == mapStorage.get(user.getId()).getId()) {

            User userUpd = mapStorage.get(user.getId());
            userUpd.setUpdatedAt(LocalDateTime.now());
            mapStorage.replace(userUpd.getId(), user, userUpd);
            System.out.println("User data updated successfully.");

        }
        else{
            throw new NullPointerException();
        }
    }

    @Override
    public void delete(User user){

        if(!mapStorage.containsKey(user.getId())) {
            throw new InvalidKeyException();
        }
        else if(user.getId() != mapStorage.get(user.getId()).getId()){
            throw new IllegalArgumentException();
        }
        else if(mapStorage.get(user.getId()).getDeletedAt() != null){
            throw new IllegalArgumentException();
        }

        user.setDeletedAt(LocalDateTime.now());
        mapStorage.replace(user.getId(), user);
        System.out.println("User " + user.getId() + " was deleted successfully.");

    }

    @Override
    public User read(int id){

        if(!mapStorage.containsKey(id)
                || mapStorage.get(id).getDeletedAt() != null){

            throw new InvalidKeyException();
        }
        return mapStorage.get(id);
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
