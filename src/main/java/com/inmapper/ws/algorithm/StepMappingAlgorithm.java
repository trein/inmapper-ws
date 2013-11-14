package com.inmapper.ws.algorithm;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
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
    
    private static final double STEP_CONVERSION = 0.45;
    
    private final LowPassFilter filter;
    private final StepDetector stepDetector;
    
    @Autowired
    public StepMappingAlgorithm(LowPassFilter filter, StepDetector stepDetector) {
        this.filter = filter;
        this.stepDetector = stepDetector;
    }
    
    @Override
    public List<UserLocation> decodePosition(MobileSessionTo session) throws InvalidMobilePositionException {
        List<UserLocation> locations = Lists.newArrayList();
        Collection<MobilePointTo> filteredPositions = this.filter.filter(session.getPositions());
        List<MobilePointTo> stepsPoints = this.stepDetector.detectSteps(filteredPositions);
        double stepLength = computeStepLength(session);
        
        UserLocation previousLocation = UserLocation.zeroValue();
        Double calibrationPhi = stepsPoints.remove(0).getHeading();
        
        for (MobilePointTo point : stepsPoints) {
            double theta = point.getHeading().doubleValue() - calibrationPhi.doubleValue();
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos(theta));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin(theta));
            
            locations.add(new UserLocation(Double.valueOf(x), Double.valueOf(y)));
        }
        return locations;
    }
    
    private double computeStepLength(MobileSessionTo session) {
        return Double.valueOf(session.getUserHeight()).doubleValue() * STEP_CONVERSION;
    }
    
}
