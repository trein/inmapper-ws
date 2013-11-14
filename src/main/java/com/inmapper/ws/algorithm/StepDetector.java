package com.inmapper.ws.algorithm;

import java.util.Collection;
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
    
    private static final int MINIMUM_WINDOW_LENGTH = 13;
    
    public List<MobilePointTo> detectSteps(List<MobilePointTo> positions) throws InvalidMobilePositionException {
        List<MobilePointTo> filteredPoints = Lists.newArrayList();
        
        validatePostiions(positions);
        
        List<MobilePointTo> inWindow = positions.subList(0, MINIMUM_WINDOW_LENGTH - 1);
        List<MobilePointTo> offWindow = positions.subList(MINIMUM_WINDOW_LENGTH, positions.size() - 1);
        
        SlidingWindow window = new SlidingWindow(inWindow);
        
        for (MobilePointTo point : offWindow) {
            window.pushNewPoint(point);
            
            if (window.isMedianAPeak()) {
                filteredPoints.add(window.getMedian());
            }
        }
        return filteredPoints;
    }
    
    private void validatePostiions(Collection<MobilePointTo> positions) throws InvalidMobilePositionException {
        if (positions.size() < (2 * MINIMUM_WINDOW_LENGTH)) {
            throw new InvalidMobilePositionException("Insufficient number of examples for algorithm.");
        }
    }
    
    private class SlidingWindow {
        
        private final List<MobilePointTo> inWindowPoints;
        
        public SlidingWindow(List<MobilePointTo> inWindowPoints) {
            this.inWindowPoints = inWindowPoints;
        }
        
        public void pushNewPoint(MobilePointTo point) {
            this.inWindowPoints.add(point);
            this.inWindowPoints.remove(0);
        }
        
        public MobilePointTo getMedian() {
            return MobilePointTo.getMedian(this.inWindowPoints);
            
        }
        
        public boolean isMedianAPeak() {
            MobilePointTo windowMedian = getMedian();
            
            for (MobilePointTo point : this.inWindowPoints) {
                Double variation = point.getVariation();
                
                if (windowMedian.getVariation().doubleValue() <= variation.doubleValue()) {
                    // Median is no a peak, we can stop searching
                    return false;
                }
            }
            return true;
        }
    }
    
}
