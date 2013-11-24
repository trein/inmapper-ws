package com.inmapper.ws.algorithm.conversion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

@Component
public class SquarePointConverter implements PointConverter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SquarePointConverter.class);
    private final SquareAngleConverter converter;
    
    @Autowired
    public SquarePointConverter(SquareAngleConverter converter) {
        this.converter = converter;
    }
    
    @Override
    public List<UserLocation> convert(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        List<UserLocation> locations = Lists.newArrayList();
        UserLocation previousLocation = UserLocation.zeroValue();
        double calibrationPhi = stepsPoints.remove(0).getHeading().doubleValue();
        double stepLength = computeStepLength(session);
        double previousTheta = 0;
        boolean firstPoint = true;
        
        for (MobilePointTo point : stepsPoints) {
            double theta = this.converter.computeTheta(point.getHeading().doubleValue(), calibrationPhi, previousTheta);
            
            LOGGER.debug("Computated theta: {}", String.valueOf(theta));
            if (!firstPoint && ((Math.abs(theta - previousTheta)) == 180)) {
                theta = previousTheta;
            }
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos((Math.PI * theta) / 180));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin((Math.PI * theta) / 180));
            
            previousLocation = new UserLocation(Double.valueOf(x), Double.valueOf(y));
            
            LOGGER.debug("Corrected theta: {}", String.valueOf(theta));
            LOGGER.debug("Mobile point: {}", point);
            LOGGER.debug("Final point: {}", previousLocation);
            LOGGER.debug("---------------------------------------------");
            
            firstPoint = false;
            previousTheta = theta;
            locations.add(previousLocation);
        }
        return locations;
    }
    
    private double computeStepLength(MobileSessionTo session) {
        return Double.valueOf(session.getUserHeight()).doubleValue() * STEP_CONVERSION;
    }
    
}
