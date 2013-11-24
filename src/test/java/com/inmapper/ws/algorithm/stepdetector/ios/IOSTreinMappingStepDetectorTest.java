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

public class IOSTreinMappingStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    private final Filter filter = new LowPassFirstOrderFilter();
    
    @Test
    public void testForMapping1() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384643993164-5d5e7dfb-7e32-4bdb-8251-e0bbb93e9cd9");
        
        assertEquals(59, mobilePoints.size());
    }
    
    @Test
    public void testForMapping2() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384644980175-c5ec3f74-8d2e-439b-a6e1-c3cf50baeb0b");
        
        assertEquals(60, mobilePoints.size());
    }
    
    @Test
    public void testForMapping3() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
        
        assertEquals(60, mobilePoints.size());
    }
    
    @Test
    public void testForMapping4() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
        
        assertEquals(60, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("ios-mapping", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        List<MobilePointTo> points = this.filter.filter(mobileSessionTo.getPositions());
        return this.stepDetector.detectSteps(points);
    }
    
}
