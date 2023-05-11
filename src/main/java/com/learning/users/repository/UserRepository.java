package com.learning.users.repository;

import com.learning.users.model.User;
import java.util.List;

public interface UserRepository {

    void create(User user) throws Exception;

    void update(User user) throws Exception;

    User read(int id) throws Exception;

    User read(String email) throws Exception;

    void delete(User user) throws Exception;

    int count() throws Exception;

    List<User> list(boolean active, int limitToList, String name) throws Exception;

    boolean findUser(int id) throws Exception;
}
