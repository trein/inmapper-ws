package com.inmapper.ws.algorithm;

import static junit.framework.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;
import com.inmapper.ws.service.SessionAuditor;

public class StepDetectorTest {
    SessionAuditor sessionAuditor = new SessionAuditor();
    StepDetector stepDetector = new StepDetector();

    @Test
    public void test5Step1() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636168201-05d056d2-fcb6-4f18-95e3-9b2940bc4987");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step2() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636211320-c2b58924-246d-4d8b-a94a-5ac63d924af1");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step3() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636247048-9bfbc1d0-e1c2-4547-9964-600f3c5411f1");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step4() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636275963-75313eb4-c28b-465d-a41e-eda6b8005e5e");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step5() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636312080-452410f1-2df8-4263-87df-a14bdc18575b");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step6() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636470666-d82ae44a-4f5c-41c4-9bf4-e5bf2b15ed0a");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step7() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636497474-88c87abb-6921-436a-93c7-1d2b3b40f5d5");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step8() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636538413-0e97c580-1559-47cf-8479-0d2cfffcae0b");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step9() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636585169-d38b5cb8-74ca-424e-b0f3-023610a25d3c");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }

    @Test
    public void test5Step10() throws InvalidMobilePositionException {
        MobileSessionTo mobileSessionTo = sessionAuditor
                .loadSession("1384636788779-25f942ea-8a4a-440e-95b5-7081c70de8d2");
        List<MobilePointTo> mobilePoints = stepDetector.detectSteps(mobileSessionTo.getPositions());

        assertSame(Integer.valueOf(5), Integer.valueOf(mobilePoints.size()));
    }
}
