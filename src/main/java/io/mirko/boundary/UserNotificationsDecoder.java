package io.mirko.boundary;

import javax.websocket.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class UserNotificationsDecoder implements Encoder.Binary<UserNotificationMessage>, Decoder.Binary<UserNotificationMessage> {
    @Override
    public UserNotificationMessage decode(ByteBuffer bytes) throws DecodeException {
        Logger.getLogger(getClass().getName()).info("Decoding.....");
        final ByteArrayInputStream is = new ByteArrayInputStream(bytes.array());
        try (ObjectInputStream oi = new ObjectInputStream(is)) {
            return (UserNotificationMessage) oi.readObject();
        } catch(Exception e) {
            Logger.getLogger(getClass().getName()).info("Could not read bytes");
            throw new DecodeException(bytes, "Could not read bytes", e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        // do nothing
    }

    @Override
    public ByteBuffer encode(UserNotificationMessage object) throws EncodeException {
        final ByteArrayOutputStream ob = new ByteArrayOutputStream();

        try (ObjectOutputStream o = new ObjectOutputStream(ob)) {
            o.writeObject(object);
            o.flush();
        } catch(IOException e) {
            throw new EncodeException(object, "Could not write bytes", e);
        }
        return ByteBuffer.wrap(ob.toByteArray());
    }

    @Override
    public boolean willDecode(ByteBuffer bytes) {
        return true;
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
