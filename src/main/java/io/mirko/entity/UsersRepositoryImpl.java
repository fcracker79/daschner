package io.mirko.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class UsersRepositoryImpl implements UsersRepository {
    @Inject
    @UsersEntityManager
    EntityManager entityManager;

    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    public User updateUser(long id, User user) {
        User existingUser = entityManager.find(User.class, id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        entityManager.flush();
        return existingUser;
    }

    public List<User> getAll() {
        return entityManager.createQuery("Select t from " + User.class.getSimpleName() + " t").getResultList();
    }

    public Long addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user.getId();
    }

}
