package com.inmapper.ws.algorithm.conversion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

/**
 * Business logic in charge of converting raw data sent by mobile clients to indoor points.
 * 
 * @author trein
 */
@Component
public class FlexiblePointConverter implements PointConverter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlexiblePointConverter.class);
    
    @Override
    public List<UserLocation> convert(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        List<UserLocation> locations = Lists.newArrayList();
        UserLocation previousLocation = UserLocation.zeroValue();
        Double calibrationPhi = stepsPoints.remove(0).getHeading();
        double stepLength = computeStepLength(session);
        
        for (MobilePointTo point : stepsPoints) {
            double theta = point.getHeading().doubleValue() - calibrationPhi.doubleValue();
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos((Math.PI * theta) / 180));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin((Math.PI * theta) / 180));
            
            previousLocation = new UserLocation(Double.valueOf(x), Double.valueOf(y));
            
            LOGGER.debug("Mobile point: {}", point);
            LOGGER.debug("Theta: {}", String.valueOf(theta));
            LOGGER.debug("Final point: {}", previousLocation);
            LOGGER.debug("---------------------------------------------");
            
            locations.add(previousLocation);
        }
        return locations;
    }
    
    private double computeStepLength(MobileSessionTo session) {
        return Double.valueOf(session.getUserHeight()).doubleValue() * STEP_CONVERSION;
    }
}
