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

    @Lock
    public void addUser(User user) {
        usersRepository.addUser(user);
        userCreated.fire(new UserCreated(user));
    }
}
