package io.mirko.boundary;

import io.mirko.entity.User;

import java.io.Serializable;


public class UserNotificationMessage implements Serializable {
    public User user;
    public UserNotificationMessage() {}

    public UserNotificationMessage(User user) {
        this.user = user;
    }
}
