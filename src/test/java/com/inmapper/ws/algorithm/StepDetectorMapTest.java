package com.inmapper.ws.algorithm;

import static junit.framework.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;
import com.inmapper.ws.service.SessionAuditor;

public class StepDetectorMapTest {
    SessionAuditor sessionAuditor = new SessionAuditor();
    StepDetector stepDetector = new StepDetector();

    @Test
    public void testMapStep1() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384638842966-102f23ab-55a4-4518-b9e9-18aa7f5726fb");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep2() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384639031259-f5879b9b-d61e-4d32-ac83-7fd09a752044");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep3() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640150118-d5be90f9-e687-4bcc-b26e-03841884271b");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep4() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640215681-9d2143db-eddf-40c7-90a2-f3f2899a82f1");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep5() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640414054-60f0f4e4-70be-49e6-91cf-d193b62ab919");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep6() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640493151-88fadf93-d6e4-48d3-80b9-f95094754ae0");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep7() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640552283-670f9d19-b51d-42d7-83d1-0d086a2b2ac3");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void testMapStep8() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384640667576-ea94f936-6dfd-489c-a213-0137bb8621c3");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(50), Integer.valueOf(mobilePoints.size()));
    }
}
