package com.inmapper.ws.service;

import java.util.List;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.MobilePosition;
import com.inmapper.ws.model.RoomLocation;

/**
 * Service orchestrating mobile sensor data conversion and persistence.
 * 
 * @author trein
 */
public interface MappingService {
    
    Long handlePosition(MobilePosition position) throws InvalidMobilePositionException;
    
    RoomLocation retrieveLocation(Long id) throws ResourceNotFoundException;
    
    List<RoomLocation> retrieveRoomLocations(String roomId) throws ResourceNotFoundException;
    
}
