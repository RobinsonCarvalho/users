package com.learning.users.repository;

import com.learning.users.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.KeyAlreadyExistsException;
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
    }

    public void update(User user){
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        mapStorage.replace(user.getEmail(), user);
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
