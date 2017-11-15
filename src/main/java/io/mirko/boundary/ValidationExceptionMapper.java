package io.mirko.boundary;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
        exception.getConstraintViolations().forEach(
                v -> builder.header("Error-Description", String.format("%s: %s", v.getInvalidValue(), v.getMessage()))
        );

        final JsonObjectBuilder body = Json.createObjectBuilder();
        final JsonObjectBuilder errors = Json.createObjectBuilder();

        exception.getConstraintViolations().forEach(
                v -> errors.add(v.getPropertyPath().toString(),v.getMessage())
        );
        body.add("errors", errors);
        builder.entity(body.build());
        return builder.build();
    }
}
