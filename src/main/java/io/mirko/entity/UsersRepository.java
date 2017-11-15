package io.mirko.entity;

import java.util.List;

public interface UsersRepository {
    List<User> getAll();
    void addUser(User user);
}
