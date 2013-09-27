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
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);
    
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
	String response = exception.getMessage();
	
	logException(exception);
	
	return Response.status(Status.BAD_REQUEST).entity(response).type(MediaType.TEXT_HTML).build();
    }
    
    private void logException(Throwable exception) {
	String exceptionMessage = exception.getMessage();
	String exceptionClass = exception.getClass().getName();
	
	LOGGER.warn("Security exception [{}] captured: {}", exceptionClass, exceptionMessage, exception);
    }
    
}
