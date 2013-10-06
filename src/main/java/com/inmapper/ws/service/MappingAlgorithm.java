package com.inmapper.ws.service;

import org.springframework.stereotype.Component;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.MobilePosition;
import com.inmapper.ws.model.RoomLocation;

/**
 * Algorithm in charge of converting mobile sensor information into relative indoor coordinates.
 * 
 * @author trein
 * @author navjot
 * @author priya
 */
@Component
public class MappingAlgorithm {
    
    public MappingAlgorithm() {
    }
    
    public RoomLocation decodePosition(MobilePosition position) throws InvalidMobilePositionException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
