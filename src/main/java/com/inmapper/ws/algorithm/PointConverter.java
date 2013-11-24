package com.inmapper.ws.algorithm;

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
public class PointConverter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PointConverter.class);
    private static final double STEP_CONVERSION = 0.45;
    
    /**
     * Conversion of raw data to indoor points.
     * 
     * @param session mobile client session information.
     * @param stepsPoints points detected as steps in mobile session.
     * @return set of indoor points.
     */
    public List<UserLocation> convert(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        // return convertRaw(session, stepsPoints);
        return convertSquare(session, stepsPoints);
    }
    
    private List<UserLocation> convertSquare(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        List<UserLocation> locations = Lists.newArrayList();
        UserLocation previousLocation = UserLocation.zeroValue();
        Double calibrationPhi = stepsPoints.remove(0).getHeading();
        double stepLength = computeStepLength(session);
        double previousTheta = 0;
        boolean firstPoint = true;
        
        for (MobilePointTo point : stepsPoints) {
            double theta = computeTheta(calibrationPhi, point, previousTheta);
            
            LOGGER.debug("Computated theta: {}", String.valueOf(theta));
            if (!firstPoint && ((Math.abs(theta - previousTheta)) == 180)) {
                theta = previousTheta;
            }
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos((Math.PI * theta) / 180));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin((Math.PI * theta) / 180));
            
            previousLocation = new UserLocation(Double.valueOf(x), Double.valueOf(y));
            
            LOGGER.debug("Mobile point: {}", point);
            LOGGER.debug("Corrected theta: {}", String.valueOf(theta));
            LOGGER.debug("Final point: {}", previousLocation);
            LOGGER.debug("---------------------------------------------");
            
            firstPoint = false;
            previousTheta = theta;
            locations.add(previousLocation);
        }
        return locations;
    }
    
    private List<UserLocation> convertRaw(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        List<UserLocation> locations = Lists.newArrayList();
        UserLocation previousLocation = UserLocation.zeroValue();
        Double calibrationPhi = stepsPoints.remove(0).getHeading();
        double previousTheta = 0;
        double stepLength = computeStepLength(session);
        
        for (MobilePointTo point : stepsPoints) {
            double theta = computeTheta(point, previousTheta, calibrationPhi);
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos((Math.PI * theta) / 180));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin((Math.PI * theta) / 180));
            
            previousLocation = new UserLocation(Double.valueOf(x), Double.valueOf(y));
            
            LOGGER.debug("Mobile point: {}", point);
            LOGGER.debug("Corrected theta: {}", String.valueOf(theta));
            LOGGER.debug("Final point: {}", previousLocation);
            LOGGER.debug("---------------------------------------------");
            
            previousTheta = theta;
            locations.add(previousLocation);
        }
        return locations;
    }
    
    private double computeTheta(Double calibrationPhi, MobilePointTo point, double previousTheta) {
        double thetaDifference = computeTheta(point, previousTheta, calibrationPhi);
        double theta;
        
        thetaDifference = (thetaDifference < 0) ? thetaDifference + 360 : thetaDifference;
        
        if ((thetaDifference >= 45) && (thetaDifference < 135)) {
            theta = 90;
        } else if ((thetaDifference >= 135) && (thetaDifference < 225)) {
            theta = 180;
        } else if ((thetaDifference >= 225) && (thetaDifference < 315)) {
            theta = 270;
        } else {
            theta = 0;
        }
        return theta;
    }
    
    private double computeTheta(MobilePointTo point, double previousTheta, Double calibrationPhi) {
        double alpha = 0.0;
        double currentTheta = point.getHeading().doubleValue() - calibrationPhi.doubleValue();
        double nexTheta = ((1 - alpha) * currentTheta) + (alpha * previousTheta);
        return nexTheta;
    }
    
    private double computeStepLength(MobileSessionTo session) {
        return Double.valueOf(session.getUserHeight()).doubleValue() * STEP_CONVERSION;
    }
}
