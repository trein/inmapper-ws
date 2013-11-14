package com.inmapper.ws.algorithm;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.to.MobilePointTo;

@Component
public class StepDetector {
    
    private static final double Z_THRESHOLD = 12;
    
    public List<MobilePointTo> detectSteps(Collection<MobilePointTo> positions) {
        List<MobilePointTo> filteredPoints = Lists.newArrayList();
        List<MobilePointTo> localPoints = Lists.newArrayList();
        
        for (MobilePointTo point : positions) {
            if (point.getZ().doubleValue() >= Z_THRESHOLD) {
                localPoints.add(point);
            } else if (!localPoints.isEmpty()) {
                MobilePointTo average = MobilePointTo.computeAverage(localPoints);
                filteredPoints.add(average);
                localPoints = Lists.newArrayList();
            }
        }
        
        if (!localPoints.isEmpty()) {
            filteredPoints.add(MobilePointTo.computeAverage(localPoints));
        }
        return filteredPoints;
    }
    
}
