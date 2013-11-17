package com.inmapper.ws.algorithm.stepdetector.ios;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.algorithm.filter.Filter;
import com.inmapper.ws.algorithm.filter.LowPassFirstOrderFilter;
import com.inmapper.ws.algorithm.stepdetection.StepDetector;
import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class IOSTreinStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    private final Filter filter = new LowPassFirstOrderFilter();
    
    @Test
    public void testForStep5() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384707690819-35c3781d-5450-45be-bff4-b6c00441e0b2");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void testForStep10() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384706024984-25abbf2d-5049-438b-981c-bdcbae55e4a6");
        
        assertEquals(10, mobilePoints.size());
    }
    
    @Test
    public void testForStep8() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384707636447-12458743-af5f-4e6d-b1a4-4eb9db6a7659");
        
        assertEquals(8, mobilePoints.size());
    }
    
    @Test
    public void testForSteps11() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384706052450-6386070b-ce38-4bb1-974d-fd9b4722513c");
        
        assertEquals(11, mobilePoints.size());
    }
    
    @Test
    public void testForSteps16() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384707672340-534ccee6-195b-49b0-8a95-ac946425bcd6");
        
        assertEquals(16, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("ios-steps", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        List<MobilePointTo> points = this.filter.filter(mobileSessionTo.getPositions());
        return this.stepDetector.detectSteps(points);
    }
    
}
