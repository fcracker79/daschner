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
    UsersRepository usersRepository;

    @GET
    public List<User> getUsers() {
        return usersRepository.getAll();
    }

    @POST
    public User addUser(User user) {
        usersRepository.addUser(user);
        return user;
    }
}
