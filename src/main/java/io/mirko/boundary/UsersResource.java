package io.mirko.boundary;

import io.mirko.entity.User;
import io.mirko.entity.UsersRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response addUser(@Valid User user) {
        usersStorage.addUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}
