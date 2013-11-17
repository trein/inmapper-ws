package com.inmapper.ws.algorithm.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.to.MobilePointTo;

/**
 * Simple implementation of a low pass filter.
 * 
 * @author trein
 */
@Component
public class LowPassFirstOrderFilter implements Filter {
    
    private static final double DEFAULT_SMOOTHING = 1.2;
    
    /*
     * Smoothing the strength of the smoothing filter; 1=no change, larger values smoothes more.
     */
    private final double smoothing = DEFAULT_SMOOTHING;
    
    /**
     * Perform values filtering according to smoothing chosen.
     * 
     * @param values collection of numbers that will be modified in place.
     * @return filtered values.
     */
    @Override
    public List<MobilePointTo> filter(Collection<MobilePointTo> points) {
        List<MobilePointTo> filteredPoints = Lists.newArrayList();
        MobilePointTo[] values = points.toArray(new MobilePointTo[0]);
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
    
    private Double computeFilter(Double currentValue, Double previousValue) {
        double previous = previousValue.doubleValue();
        double current = currentValue.doubleValue();
        return Double.valueOf(previous + ((current - previous) / this.smoothing));
    }
}
