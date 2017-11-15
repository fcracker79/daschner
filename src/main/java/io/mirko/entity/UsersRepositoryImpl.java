package io.mirko.entity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class UsersRepositoryImpl implements UsersRepository {
    private final List<User> allUsers = new ArrayList<>();

    public UsersRepositoryImpl() {
        allUsers.add(new User("John3", "Burns"));
        allUsers.add(new User("David", "Mills"));
    }

    public List<User> getAll() {
        return Collections.unmodifiableList(this.allUsers);
    }

    public void addUser(User user) {
        this.allUsers.add(user);
    }

}
