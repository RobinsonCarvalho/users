package com.learning.users.repository;

import com.learning.users.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository{

    Map<Integer, User> mapStorage = new HashMap<>();

    public void create(User user){
        int idUser = count() + 1;
        user.setId(idUser);
        mapStorage.put(idUser, user);
    }

    public int count(){
        return mapStorage.size();
    }
}
