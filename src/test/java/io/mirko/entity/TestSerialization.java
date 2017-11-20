package io.mirko.entity;

import io.mirko.boundary.UserNotificationMessage;
import io.mirko.boundary.UserNotificationsDecoder;
import org.junit.Assert;
import org.junit.Test;

public class TestSerialization {
    @Test
    public void test() throws Exception {
        final UserNotificationMessage m = new UserNotificationMessage(new User("John", "Burns"));
        final UserNotificationsDecoder d = new UserNotificationsDecoder();
        final UserNotificationMessage m2 = d.decode(d.encode(m));
        Assert.assertEquals("John", m2.user.getFirstName());
        Assert.assertEquals("Burns", m2.user.getLastName());

    }
}
