package com.inmapper.ws.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.to.MobilePositionTo;
import com.inmapper.ws.model.to.RoomMappingTo;

@Service
public class MappingRESTFacadeImpl implements MappingRESTFacade {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MappingRESTFacadeImpl.class);
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private IdGenerator generator;
    
    @Autowired
    private MappingService service;
    
    @Override
    public Response health() {
        LOGGER.debug("Health check received from {}", this.request.getRemoteHost()); //$NON-NLS-1$
        
        return Response.ok("Health check: Alive").build();
    }
    
    @Override
    public Response identification() {
        return Response.ok(this.generator.next()).build();
    }
    
    @Override
    public Response positions(MobilePositionTo position) throws InvalidMobilePositionException {
        LOGGER.debug("POST position received with {}", position); //$NON-NLS-1$
        String id = this.service.handlePosition(position);
        
        return Response.ok(id).build();
    }
    
    @Override
    public Response mappings(String roomId) throws ResourceNotFoundException {
        LOGGER.debug("GET room locations received with room id {}", roomId); //$NON-NLS-1$
        RoomMappingTo mapping = this.service.retrieveRoomLocations(roomId);
        
        return Response.ok(mapping).build();
    }
}
