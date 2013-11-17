package com.inmapper.ws.algorithm.stepdetection;

import java.util.Collection;
import java.util.List;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;

public class SlidingWindow {
    
    private static final int MINIMUM_WINDOW_LENGTH = 13;
    private static final double STANDARD_DEVIATION_THRESHOLD = 0.84;
    private static final int PEAK_EXAMPLES_SPAN_THRESHOLD = 8;
    
    private int examplesCounter = MINIMUM_WINDOW_LENGTH;
    private int lastPeakCounter = 0;
    
    private final List<MobilePointTo> windowPoints;
    
    public SlidingWindow(List<MobilePointTo> inWindowPoints) {
        this.windowPoints = inWindowPoints;
    }
    
    public void pushNewPoint(MobilePointTo point) {
        this.examplesCounter++;
        this.windowPoints.add(point);
        this.windowPoints.remove(0);
    }
    
    public MobilePointTo getMedian() {
        return MobilePointTo.getMedian(this.windowPoints);
        
    }
    
    public boolean isMedianAPeak() {
        MobilePointTo windowMedian = getMedian();
        Double standardDeviation = MobilePointTo.computeStandardDeviation(this.windowPoints);
        
        // Check minimum standard deviation for the window examples
        if (!hasWindowMinimumDeviation(standardDeviation)) { return false; }
        
        // Check if there is a peak in the current window
        for (MobilePointTo point : this.windowPoints) {
            Double variation = point.getAccelerationNorm();
            
            if (windowMedian.getAccelerationNorm().doubleValue() < variation.doubleValue()) {
                // Median is no a peak, we can stop searching
                return false;
            }
        }
        
        // Check if the identified peak in the current window is not noise
        if (!isExamplesSpanEnogh()) { return false; }
        
        this.lastPeakCounter = this.examplesCounter;
        return true;
    }
    
    private boolean isExamplesSpanEnogh() {
        return (this.examplesCounter - this.lastPeakCounter) > PEAK_EXAMPLES_SPAN_THRESHOLD;
    }
    
    private boolean hasWindowMinimumDeviation(Double standardDeviation) {
        return standardDeviation.doubleValue() >= STANDARD_DEVIATION_THRESHOLD;
    }
    
    private static void validatePostiions(Collection<MobilePointTo> positions) throws InvalidMobilePositionException {
        if (positions.size() < (2 * MINIMUM_WINDOW_LENGTH)) { throw new InvalidMobilePositionException(
                "Insufficient number of examples for algorithm."); }
    }
    
    public static List<MobilePointTo> getInitialWindowList(List<MobilePointTo> positions) throws InvalidMobilePositionException {
        validatePostiions(positions);
        return positions.subList(0, MINIMUM_WINDOW_LENGTH - 1);
    }
    
    public static List<MobilePointTo> getResidualWindowList(List<MobilePointTo> positions) throws InvalidMobilePositionException {
        validatePostiions(positions);
        return positions.subList(MINIMUM_WINDOW_LENGTH, positions.size() - 1);
    }
    
}
