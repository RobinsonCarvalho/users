package com.learning.users.repository;

import com.learning.users.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.*;

public class UserRepositoryInMemory implements UserRepository{

    Map<String, User> mapStorage = new HashMap<>();

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

    public void update(User user){

        Map<String, LocalDateTime> storageUserUpdated = new HashMap<>();

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        else if(!mapStorage.containsKey(user.getEmail())){
            throw new InvalidKeyException(user.getEmail());
        }
      else{

            User userUpd;

            userUpd = mapStorage.get(user.getEmail());

            if(user.getId() != userUpd.getId()){
                throw new IllegalArgumentException();
            }

            userUpd.setName(user.getName());
            userUpd.setLastName(user.getLastName());
            userUpd.setDateOfBirth(user.getDateOfBirth());
            userUpd.setEmail(user.getEmail());
            userUpd.setPhone(user.getPhone());
            userUpd.setGitHubProfile(user.getGitHubProfile());
            mapStorage.replace(userUpd.getEmail(), user, userUpd);

            //Created to store the date which the user was updated
            if(storageUserUpdated.containsKey(userUpd.getEmail())){
                storageUserUpdated.replace(userUpd.getEmail(), LocalDateTime.now());
            }
            else{
                storageUserUpdated.put(userUpd.getEmail(), LocalDateTime.now());
            }

            System.out.println("User data updated successfully.");
        }

    }

    public User read(String email){

        if(!mapStorage.containsKey(email)){
            throw new InvalidKeyException(email);
        }

        return mapStorage.get(email);
    }

    public int count(){

        return mapStorage.size();

    }

}
