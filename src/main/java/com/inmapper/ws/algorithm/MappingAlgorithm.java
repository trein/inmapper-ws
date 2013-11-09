package com.inmapper.ws.algorithm;

import java.util.List;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobileSessionTo;

public interface MappingAlgorithm {
    
    List<UserLocation> decodePosition(MobileSessionTo position) throws InvalidMobilePositionException;
    
}
