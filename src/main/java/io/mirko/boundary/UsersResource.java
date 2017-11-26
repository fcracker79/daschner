package io.mirko.boundary;

import io.mirko.control.Tracked;
import io.mirko.entity.User;
import io.mirko.entity.UsersRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tracked
public class UsersResource {
    @Inject
    UsersStorage usersStorage;

    @Context
    UriInfo uriInfo;

    @GET
    public List<User> getUsers() {
        return usersStorage.getAllUsers();
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") long id) {
        return usersStorage.getUser(id);
    }

    @POST
    public Response addUser(@Valid User user) {
        URI userUri = uriInfo.getBaseUriBuilder().path(UsersResource.class).path(UsersResource.class, "getUser")
                .build(usersStorage.addUser(user));
        return Response.created(userUri).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@Valid User user, @PathParam("id") long id) {
        URI userUri = uriInfo.getBaseUriBuilder().path(UsersResource.class).path(UsersResource.class, "getUser")
                .build(usersStorage.updateUser(id, user));
        return Response.status(Response.Status.OK).entity(userUri).build();
    }
}
