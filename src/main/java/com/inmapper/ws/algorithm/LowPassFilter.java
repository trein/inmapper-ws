package com.inmapper.ws.algorithm;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.to.MobilePointTo;

/**
 * Simple implementation of a low pass filter.
 * 
 * @author trein
 */
@Component
public class LowPassFilter {
    
    private final int smoothing;
    
    /**
     * Filter creation.
     * 
     * @param smoothing the strength of the smoothing filter; 1=no change, larger values smoothes
     *        more.
     */
    public LowPassFilter(int smoothing) {
        this.smoothing = smoothing;
    }
    
    /**
     * Perform values filtering according to smoothing chosen.
     * 
     * @param values collection of numbers that will be modified in place.
     * @return filtered values.
     */
    public Collection<MobilePointTo> filter(Collection<MobilePointTo> points) {
        Collection<MobilePointTo> filteredPoints = Lists.newArrayList();
        MobilePointTo[] values = (MobilePointTo[]) points.toArray();
        MobilePointTo previousPoint = values[0];
        
        for (int i = 1; i < values.length; ++i) {
            MobilePointTo currentPoint = values[i];
            Double newX = computeFilter(currentPoint.getX(), previousPoint.getX());
            Double newY = computeFilter(currentPoint.getY(), previousPoint.getY());
            Double newZ = computeFilter(currentPoint.getZ(), previousPoint.getZ());
            Double newHeading = computeFilter(currentPoint.getHeading(), previousPoint.getHeading());
            
            filteredPoints.add(new MobilePointTo(newX, newY, newZ, newHeading));
        }
        return filteredPoints;
    }
    
    public Double computeFilter(Double currentValue, Double previousValue) {
        double previous = previousValue.doubleValue();
        double current = currentValue.doubleValue();
        return Double.valueOf(previous + ((current - previous) / this.smoothing));
    }
}
