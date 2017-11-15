package io.mirko.boundary;

import io.mirko.entity.User;
import io.mirko.entity.UsersRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {
    @Inject
    UsersStorage usersStorage;

    @GET
    public List<User> getUsers() {
        return usersStorage.getAllUsers();
    }

    @POST
    public User addUser(User user) {
        usersStorage.addUser(user);
        return user;
    }
}
