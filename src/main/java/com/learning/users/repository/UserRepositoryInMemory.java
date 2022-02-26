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
    Map<String, LocalDateTime> StorageUserUpdated = new HashMap<>();
    Map<String, LocalDateTime> storageUserDeleted = new HashMap<>();

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
        mapStorage.replace(user.getEmail(), user);
        StorageUserUpdated.replace(user.getEmail(), LocalDateTime.now());
        System.out.println("User data updated successfully.");
    }

    @Override
    public void delete(String email){
        if(!mapStorage.containsKey(email) || storageUserDeleted.containsKey(email)){
            throw new InvalidKeyException(email);
        }
        storageUserDeleted.put(email, LocalDateTime.now());
        System.out.println("User " + email.toUpperCase() + " was successfully removed.");
    }

    @Override
    public User read(String email){
        if(!mapStorage.containsKey(email) || storageUserDeleted.containsKey(email)){
            throw new InvalidKeyException(email);
        }
        return mapStorage.get(email);
    }

    public int count(){
        return mapStorage.size();
    }

}
