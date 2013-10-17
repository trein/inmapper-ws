package com.inmapper.ws.service;

import org.springframework.stereotype.Component;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePositionTo;

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
    
    public UserLocation decodePosition(MobilePositionTo position) throws InvalidMobilePositionException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
