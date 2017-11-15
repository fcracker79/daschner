package io.mirko.entity;

public class UserCreated {
    private final User user;

    public UserCreated(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
