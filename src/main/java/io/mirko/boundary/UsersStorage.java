package io.mirko.boundary;


import io.mirko.entity.User;
import io.mirko.entity.UserCreated;
import io.mirko.entity.UsersRepository;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Singleton
public class UsersStorage {
    @Inject
    UsersRepository usersRepository;

    @Inject
    Event<UserCreated> userCreated;

    @Lock(LockType.READ)
    public List<User> getAllUsers() {
        return usersRepository.getAll();
    }

    @Lock(LockType.READ)
    public User getUser(long id) {
        return usersRepository.getUser(id);
    }

    @Lock(LockType.WRITE)
    public User updateUser(long id, User user) {
        return usersRepository.updateUser(id, user);
    }
    @Lock
    public Long addUser(User user) {
        final Long id = usersRepository.addUser(user);
        userCreated.fireAsync(new UserCreated(user));
        return id;
    }
}
