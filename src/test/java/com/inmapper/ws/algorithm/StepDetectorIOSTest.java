package com.inmapper.ws.algorithm;

import static junit.framework.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;
import com.inmapper.ws.service.SessionAuditor;

public class StepDetectorIOSTest {
    SessionAuditor sessionAuditor = new SessionAuditor();
    StepDetector stepDetector = new StepDetector();
    Filter filter = new LowPassFirstOrderFilter();
    
    @Test
    public void testForStep20() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-steps/1384642987877-432233f4-1cff-4e5e-a675-6737b1b464ba");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(20), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForSteps16() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-steps/1384645705006-ed232730-16c0-4777-9b72-c50601dbac80");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(16), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForSteps17() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-steps/1384645859833-c1c734f0-2eb1-47b1-af20-ff0a3cc2a9ed");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(17), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForMapping1() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-mapping/1384643993164-5d5e7dfb-7e32-4bdb-8251-e0bbb93e9cd9");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(59), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForMapping2() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-mapping/1384644980175-c5ec3f74-8d2e-439b-a6e1-c3cf50baeb0b");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(60), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForMapping3() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-mapping/1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(60), Integer.valueOf(mobilePoints.size()));
    }
    
    @Test
    public void testForMapping4() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = this.sessionAuditor
                .loadSession("ios-mapping/1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
        List<MobilePointTo> mobilePoints = this.stepDetector.detectSteps(mobileSessionTo.getPositions());
        
        assertSame(Integer.valueOf(60), Integer.valueOf(mobilePoints.size()));
    }
    
}
