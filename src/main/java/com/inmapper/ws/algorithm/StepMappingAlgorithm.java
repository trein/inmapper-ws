package com.inmapper.ws.algorithm;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

/**
 * Algorithm in charge of converting mobile sensor information into relative indoor coordinates.
 * 
 * @author trein
 * @author navjot
 * @author priya
 */
@Component
public class StepMappingAlgorithm implements MappingAlgorithm {
    
    private final LowPassFilter filter;
    private final StepDetector stepDetector;
    private final PointConverter converter;
    
    @Autowired
    public StepMappingAlgorithm(LowPassFilter filter, StepDetector stepDetector, PointConverter converter) {
        this.filter = filter;
        this.stepDetector = stepDetector;
        this.converter = converter;
    }
    
    @Override
    public List<UserLocation> decodePosition(MobileSessionTo session) throws InvalidMobilePositionException {
        Collection<MobilePointTo> filteredPositions = this.filter.filter(session.getPositions());
        List<MobilePointTo> stepsPoints = this.stepDetector.detectSteps(filteredPositions);
        
        return this.converter.convert(session, stepsPoints);
    }
    
}
