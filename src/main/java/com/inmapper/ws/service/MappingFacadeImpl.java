package com.inmapper.ws.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.MobilePosition;
import com.inmapper.ws.model.RoomLocation;

@Service
public class MappingFacadeImpl implements MappingFacade {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MappingFacadeImpl.class);
    
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
    public Response positions(MobilePosition position) throws InvalidMobilePositionException {
        LOGGER.debug("POST position received with {}", position); //$NON-NLS-1$
        Long id = this.service.handlePosition(position);
        
        return Response.ok(id).build();
    }
    
    @Override
    public Response locations(Long id) throws ResourceNotFoundException {
        LOGGER.debug("GET location received with id {}", id); //$NON-NLS-1$
        RoomLocation location = this.service.retrieveLocation(id);
        
        return Response.ok(location).build();
    }
    
    @Override
    public Response roomLocations(String roomId) throws ResourceNotFoundException {
        LOGGER.debug("GET room locations received with room id {}", roomId); //$NON-NLS-1$
        List<RoomLocation> locations = this.service.retrieveRoomLocations(roomId);
        
        return Response.ok(locations).build();
    }
}
