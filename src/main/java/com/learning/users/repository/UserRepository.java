package com.learning.users.repository;

import com.learning.users.model.User;
import java.util.List;

public interface UserRepository {

    void create(User user);

    void update(User user);

    User read(int id);

    void delete(User user);

    int count();

    List<User> list(boolean active, int limitToList, String name);

    User searchById(int id);

}
