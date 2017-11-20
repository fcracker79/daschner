package io.mirko.boundary;

import io.mirko.entity.UserCreated;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@ServerEndpoint(value="/user_notifications", decoders=UserNotificationsDecoder.class, encoders=UserNotificationsDecoder.class)
@ApplicationScoped
public class UserNotificationServer {
    private final Logger logger = Logger.getLogger(UserNotificationMessage.class.getName());
    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void openSession(Session session) {
        Logger.getLogger(UserNotificationServer.class.getName()).info("openSession");
        sessions.put(session.getId(), session);
        logger.info(String.format("Current sessions: %s", sessions.size()));
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info(String.format("Received String message %s", message));
    }

    @OnMessage
    public void onMessage(UserNotificationMessage message, Session session) {
        logger.info(String.format("Received Object message %s", message.toString()));
    }

    @OnClose
    public void closeSession(Session session) {
        logger.info("closeSession");
        sessions.remove(session.getId());
        logger.info(String.format("Current sessions: %s", sessions.size()));
    }

    public void onUserCreated(@Observes UserCreated userCreated) {
        logger.info(String.format("User just created: %s", userCreated.getUser()));
        for (Session s: sessions.values()) {
            logger.info(String.format("Sending message to session %s", s));
            try {
                s.getBasicRemote().sendObject(new UserNotificationMessage(userCreated.getUser()));
            } catch (EncodeException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info(String.format("Current sessions: %s", sessions.size()));
        logger.info(String.format("I am %s, id %s", this.getClass().getName(), System.identityHashCode(this)));
    }
}
