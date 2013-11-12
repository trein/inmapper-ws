package com.inmapper.ws.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Provider
@Component
public class ResourceExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        String response = "Invalid request";

        logException(exception);

        return Response.status(Status.BAD_REQUEST).entity(response).type(MediaType.TEXT_HTML).build();
    }

    private void logException(Throwable exception) {
        String exceptionMessage = exception.getMessage();
        String exceptionClass = exception.getClass().getName();

        exception.printStackTrace();

        LOGGER.warn("Exception [{}] captured: {}", exceptionClass, exceptionMessage);
    }

}
