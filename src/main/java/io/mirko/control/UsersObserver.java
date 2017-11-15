package io.mirko.control;

import io.mirko.entity.User;
import io.mirko.entity.UserCreated;

import javax.enterprise.event.Observes;
import java.util.logging.Logger;

public class UsersObserver {
    public void onUserCreated(@Observes UserCreated userCreated) {
        Logger.getLogger(User.class.getName()).info(String.format("User just created: %s", userCreated.getUser()));
    }
}
