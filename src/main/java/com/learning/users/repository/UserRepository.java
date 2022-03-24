package com.learning.users.repository;

import com.learning.users.model.User;

import java.util.List;

public interface UserRepository {

    void create(User user);

    void update(User user);

    User read(String email);

    void delete(User user);

    int count();

    List<User> listUser(String userSearch);

}
