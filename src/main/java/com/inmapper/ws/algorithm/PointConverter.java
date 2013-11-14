package com.inmapper.ws.algorithm;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

@Component
public class PointConverter {
    
    private static final double STEP_CONVERSION = 0.45;
    
    public List<UserLocation> convert(MobileSessionTo session, List<MobilePointTo> stepsPoints) {
        List<UserLocation> locations = Lists.newArrayList();
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
