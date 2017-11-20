package io.mirko.entity;

import io.mirko.boundary.UserNotificationMessage;
import io.mirko.boundary.UserNotificationsDecoder;
import org.glassfish.tyrus.client.ClientManager;
import org.junit.Test;

import javax.websocket.*;
import java.net.URI;

public class TestWebsocket {
    @ClientEndpoint(encoders=UserNotificationsDecoder.class, decoders=UserNotificationsDecoder.class)
    public static class WebsocketClientEndpoint {
        /**
         * Callback hook for Connection open events.
         *
         * @param userSession the userSession which is opened.
         */
        @OnOpen
        public void onOpen(Session userSession) {
            System.out.println("opening websocket");
        }

        /**
         * Callback hook for Connection close events.
         *
         * @param userSession the userSession which is getting closed.
         * @param reason      the reason for connection close
         */
        @OnClose
        public void onClose(Session userSession, CloseReason reason) {
            System.out.println("closing websocket");
        }

        /**
         * Callback hook for Message Events. This method will be invoked when a client send a message.
         *
         * @param message The text message
         */
        @OnMessage
        public void onMessage(String message) {
            System.out.println("Received " + message);
        }

        @OnMessage
        public void onMessage(UserNotificationMessage message) {
            System.out.println("Received " + message.user.getFirstName() + ", " + message.user.getLastName());
        }

    }
        @Test
    public void test() {
        final UserNotificationMessage m = new UserNotificationMessage(new User("Mirko", "Bonasorte"));
        try {
            ClientManager client = ClientManager.createClient();
            final Session session = client.connectToServer(WebsocketClientEndpoint.class, new URI("ws://localhost:8080/dashner/user_notifications"));
            session.getBasicRemote().sendObject(new UserNotificationMessage(new User("Mirko", "Bonasorte")));

            // wait 5 seconds for messages from websocket
            while (true) {
                Thread.sleep(50000);
            }

        } catch (Exception ex) {
            System.err.println(String.format("%s exception: %s", ex.getClass(), ex.getMessage()));
        }
    }
}
