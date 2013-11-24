package com.inmapper.ws.algorithm.stepdetection;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;

/**
 * Simple step detector using accelerometer data.
 * 
 * @author trein
 */
@Component
public class StepDetector {
    
    public List<MobilePointTo> detectSteps(List<MobilePointTo> positions) throws InvalidMobilePositionException {
        List<MobilePointTo> filteredPoints = Lists.newArrayList();
        List<MobilePointTo> inWindow = SlidingWindow.getInitialWindowList(positions);
        List<MobilePointTo> offWindow = SlidingWindow.getResidualWindowList(positions);
        
        SlidingWindow window = new SlidingWindow(inWindow);
        
        for (MobilePointTo point : Lists.newArrayList(offWindow)) {
            window.pushNewPoint(point);
            
            if (window.isMedianAPeak()) {
                filteredPoints.add(window.getPeak());
            }
        }
        return filteredPoints;
    }
    
}
