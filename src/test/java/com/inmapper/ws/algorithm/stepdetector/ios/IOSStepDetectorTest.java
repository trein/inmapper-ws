package com.inmapper.ws.algorithm.stepdetector.ios;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.algorithm.StepDetector;
import com.inmapper.ws.algorithm.filter.Filter;
import com.inmapper.ws.algorithm.filter.LowPassFirstOrderFilter;
import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class IOSStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    private final Filter filter = new LowPassFirstOrderFilter();
    
    @Test
    public void testForStep20() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384642987877-432233f4-1cff-4e5e-a675-6737b1b464ba");
        
        assertEquals(20, mobilePoints.size());
    }
    
    @Test
    public void testForSteps16() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384645705006-ed232730-16c0-4777-9b72-c50601dbac80");
        
        assertEquals(16, mobilePoints.size());
    }
    
    @Test
    public void testForSteps17() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384645859833-c1c734f0-2eb1-47b1-af20-ff0a3cc2a9ed");
        
        assertEquals(17, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("ios-steps", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        List<MobilePointTo> points = this.filter.filter(mobileSessionTo.getPositions());
        return this.stepDetector.detectSteps(points);
    }
    
}
