package com.inmapper.ws.algorithm.stepdetector.android;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.algorithm.StepDetector;
import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class AndroidMappingStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    
    @Test
    public void testMapStep1() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384638842966-102f23ab-55a4-4518-b9e9-18aa7f5726fb");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep2() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384639031259-f5879b9b-d61e-4d32-ac83-7fd09a752044");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep3() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640150118-d5be90f9-e687-4bcc-b26e-03841884271b");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep4() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640215681-9d2143db-eddf-40c7-90a2-f3f2899a82f1");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep5() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640414054-60f0f4e4-70be-49e6-91cf-d193b62ab919");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep6() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640493151-88fadf93-d6e4-48d3-80b9-f95094754ae0");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep7() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640552283-670f9d19-b51d-42d7-83d1-0d086a2b2ac3");
        
        assertEquals(50, mobilePoints.size());
    }
    
    @Test
    public void testMapStep8() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384640667576-ea94f936-6dfd-489c-a213-0137bb8621c3");
        
        assertEquals(50, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("android-mapping", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        return this.stepDetector.detectSteps(mobileSessionTo.getPositions());
    }
}
