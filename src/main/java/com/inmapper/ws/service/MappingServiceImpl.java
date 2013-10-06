package com.inmapper.ws.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.Position;

@Service
public class MappingServiceImpl implements MappingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MappingServiceImpl.class);
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private IdGenerator generator;
    
    @Override
    public Response identification() {
        return Response.ok(this.generator.next()).build();
    }
    
    @Override
    public Response health() {
        LOGGER.debug("Health check received from {}", this.request.getRemoteHost()); //$NON-NLS-1$
        
        return Response.ok("Health check: Alive").build();
    }
    
    @Override
    public Response positions(Position position) {
        LOGGER.debug("POST position received {}", position); //$NON-NLS-1$
        return Response.ok().build();
    }
    
    @Override
    public Response positionsId(String id) throws ResourceNotFoundException {
        return Response.ok().build();
    }
    
}
