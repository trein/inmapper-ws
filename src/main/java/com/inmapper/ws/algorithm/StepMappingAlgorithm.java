package com.inmapper.ws.algorithm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.inmapper.ws.algorithm.conversion.PointConverter;
import com.inmapper.ws.algorithm.filtering.Filter;
import com.inmapper.ws.algorithm.stepdetection.StepDetector;
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StepMappingAlgorithm.class);
    
    private final Filter filter;
    private final StepDetector stepDetector;
    private final PointConverter converter;
    
    @Autowired
    public StepMappingAlgorithm(@Qualifier("lowPassFirstOrderFilter") Filter filter, StepDetector stepDetector,
            @Qualifier("squarePointConverter") PointConverter converter) {
        this.filter = filter;
        this.stepDetector = stepDetector;
        this.converter = converter;
    }
    
    @Override
    public List<UserLocation> decodePosition(MobileSessionTo session) throws InvalidMobilePositionException {
        List<MobilePointTo> filteredPositions = this.filter.filter(session.getPositions());
        LOGGER.debug("Finished filtering");
        
        List<MobilePointTo> stepsPoints = this.stepDetector.detectSteps(filteredPositions);
        LOGGER.debug("Finished Steps Detection: {} steps detected", String.valueOf(stepsPoints.size()));
        
        List<UserLocation> convertedPoints = this.converter.convert(session, stepsPoints);
        LOGGER.debug("Finished conversion");
        
        return convertedPoints;
    }
    
}
