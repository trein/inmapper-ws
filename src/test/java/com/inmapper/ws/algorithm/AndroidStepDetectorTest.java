package com.inmapper.ws.algorithm;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class AndroidStepDetectorTest {
    
    private final SessionAuditor sessionAuditor = new SessionAuditor();
    private final StepDetector stepDetector = new StepDetector();
    
    @Test
    public void test5Step1() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636168201-05d056d2-fcb6-4f18-95e3-9b2940bc4987");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step2() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636211320-c2b58924-246d-4d8b-a94a-5ac63d924af1");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step3() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636247048-9bfbc1d0-e1c2-4547-9964-600f3c5411f1");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step4() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636275963-75313eb4-c28b-465d-a41e-eda6b8005e5e");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step5() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636312080-452410f1-2df8-4263-87df-a14bdc18575b");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step6() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636470666-d82ae44a-4f5c-41c4-9bf4-e5bf2b15ed0a");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step7() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636497474-88c87abb-6921-436a-93c7-1d2b3b40f5d5");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step8() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636538413-0e97c580-1559-47cf-8479-0d2cfffcae0b");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step9() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636585169-d38b5cb8-74ca-424e-b0f3-023610a25d3c");
        
        assertEquals(5, mobilePoints.size());
    }
    
    @Test
    public void test5Step10() throws InvalidMobilePositionException {
        List<MobilePointTo> mobilePoints = retrieveSteps("1384636788779-25f942ea-8a4a-440e-95b5-7081c70de8d2");
        
        assertEquals(5, mobilePoints.size());
    }
    
    private List<MobilePointTo> retrieveSteps(String filename) throws InvalidMobilePositionException {
        File existentFileForData = FileGenerator.existentFileForData("android-steps", filename);
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(existentFileForData);
        return this.stepDetector.detectSteps(mobileSessionTo.getPositions());
    }
}
