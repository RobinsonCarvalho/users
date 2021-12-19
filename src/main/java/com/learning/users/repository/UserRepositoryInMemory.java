package com.learning.users.repository;

import com.learning.users.model.User;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository{

    Map<Integer, User> mapStorage = new HashMap<>();

    public void create(@NotNull User user){

        int idUser = mapStorage.size();
        user.setId(idUser);
        mapStorage.put(idUser, user);

    }

    public int count(){
        return mapStorage.size();
    }
}
