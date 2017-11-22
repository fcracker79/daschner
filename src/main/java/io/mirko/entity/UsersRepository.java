package io.mirko.entity;

import java.util.List;

public interface UsersRepository {
    User getUser(long id);
    User updateUser(long id, User user);
    List<User> getAll();
    Long addUser(User user);
}
