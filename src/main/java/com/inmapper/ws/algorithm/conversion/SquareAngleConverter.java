package com.inmapper.ws.algorithm.conversion;

import org.springframework.stereotype.Component;

@Component
public class SquareAngleConverter {
    
    private static final double ALPHA = 0.0;
    
    public double computeTheta(double heading, double calibrationPhi, double previousTheta) {
        double momentum = computeMomentum(heading, previousTheta, calibrationPhi);
        double theta;
        
        if ((momentum >= 45) && (momentum < 135)) {
            theta = 90;
        } else if ((momentum >= 135) && (momentum < 225)) {
            theta = 180;
        } else if ((momentum >= 225) && (momentum < 315)) {
            theta = 270;
        } else {
            theta = 0;
        }
        return theta;
    }
    
    private double computeMomentum(double heading, double previousTheta, double calibrationPhi) {
        double currentTheta = heading - calibrationPhi;
        currentTheta = (currentTheta < 0) ? currentTheta + 360 : currentTheta;
        
        return ((1 - ALPHA) * currentTheta) + (ALPHA * previousTheta);
    }
}
