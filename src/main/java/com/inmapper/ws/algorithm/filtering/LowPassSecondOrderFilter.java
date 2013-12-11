package com.inmapper.ws.algorithm.filtering;

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
public class LowPassSecondOrderFilter implements Filter {
    
    // private static final int ACC_NOISE_ATTENUATION = 3;
    
    private static final double RATE_HZ = 20;
    private static final double CUTOFF_FREQ_HZ = 15;
    
    private final double filterConstant;
    
    public LowPassSecondOrderFilter() {
        double dt = 1.0 / RATE_HZ;
        double RC = 1.0 / CUTOFF_FREQ_HZ;
        this.filterConstant = dt / (dt + RC);
    }
    
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
            
            previousPoint = new MobilePointTo(newX, newY, newZ, newHeading);
            filteredPoints.add(previousPoint);
        }
        return filteredPoints;
    }
    
    private Double computeFilter(Double currentValue, Double previousValue) {
        double alpha = this.filterConstant;
        
        // if (adaptive) {
        // double d = clamp((fabs(Norm(x, y, z) - Norm(accel.x, accel.y, accel.z)) /
        // kAccelerometerMinStep) - 1.0);
        // alpha = (((1.0 - d) * this.filterConstant) / ACC_NOISE_ATTENUATION) + (d *
        // this.filterConstant);
        // }
        
        double previous = previousValue.doubleValue();
        double current = currentValue.doubleValue();
        return Double.valueOf(previous + ((current - previous) * alpha));
    }
    
    // private double clamp(double value) {
    // return Math.max(0, Math.min(1, value));
    //
    // }
}
