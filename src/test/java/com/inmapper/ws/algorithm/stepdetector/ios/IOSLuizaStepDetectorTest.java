package com.inmapper.ws.algorithm.stepdetector.ios;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.algorithm.filtering.Filter;
import com.inmapper.ws.algorithm.filtering.LowPassFirstOrderFilter;
import com.inmapper.ws.algorithm.stepdetection.StepDetector;
import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class IOSLuizaStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    private final Filter filter = new LowPassFirstOrderFilter();
    
    @Test
    public void testForStep5() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384707996983-c8ecd0f0-5c81-48fe-b76d-963b63de949a");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void testForStep10() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384708043574-029ba177-3f12-4747-88b7-0a36e790a2a5");
        
        assertEquals(10, mobilePoints.size());
    }
    
    @Test
    public void testForStep15() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384708084600-d3cf49f4-53e7-4698-a533-a640b690e1ae");
        
        assertEquals(15, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("ios-steps", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        List<MobilePointTo> points = this.filter.filter(mobileSessionTo.getPositions());
        return this.stepDetector.detectSteps(points);
    }
    
}
