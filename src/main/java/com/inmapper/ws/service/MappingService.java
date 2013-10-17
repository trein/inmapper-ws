package com.inmapper.ws.service;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.to.MobilePositionTo;
import com.inmapper.ws.model.to.RoomMappingTo;

/**
 * Service orchestrating mobile sensor data conversion and persistence.
 * 
 * @author trein
 */
public interface MappingService {
    
    String handlePosition(MobilePositionTo position) throws InvalidMobilePositionException;
    
    RoomMappingTo retrieveRoomLocations(String roomId) throws ResourceNotFoundException;
    
}
