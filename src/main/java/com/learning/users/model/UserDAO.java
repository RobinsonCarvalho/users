package com.learning.users.model;

import com.learning.users.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDAO implements UserRepository {

    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User read(int id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<User> list(boolean active, int limitToList, String name) {
        Query query = entityManager.createQuery("SELECT * from user");
        return query.getResultList();
    }

    @Override
    public User searchById(int id) {
        return entityManager.find(User.class, id);
    }

}
