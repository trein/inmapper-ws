package com.inmapper.ws.algorithm;

import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.algorithm.stepdetection.StepDetector;
import com.inmapper.ws.evaluation.components.FileGenerator;
import com.inmapper.ws.evaluation.components.SessionAuditor;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

public class DiscreteHeadingIOS {
    
    SessionAuditor sessionAuditor = new SessionAuditor();
    StepDetector stepDetector = new StepDetector();
    List<MobilePointTo> mobilePointsDiscrete = new ArrayList<>();
    List<MobilePointTo> mobilePoints;
    
    @Test
    public void test5Step1() throws InvalidMobilePositionException {
        File file = FileGenerator.existentFileForData("ios-mapping", "1384643993164-5d5e7dfb-7e32-4bdb-8251-e0bbb93e9cd9");
        MobileSessionTo mobileSessionTo = this.sessionAuditor.loadSession(file);
        
        this.mobilePoints = mobileSessionTo.getPositions();
        Double calibrationPhi = this.mobilePoints.get(0).getHeading();
        for (MobilePointTo mobilePointTo : this.mobilePoints) {
            if (((mobilePointTo.getHeading() - calibrationPhi) >= 45) && ((mobilePointTo.getHeading() - calibrationPhi) < 135)) {
                this.mobilePointsDiscrete.add(new MobilePointTo(mobilePointTo.getX(), mobilePointTo.getY(), mobilePointTo.getZ(),
                        Double.valueOf(90)));
            } else if (((mobilePointTo.getHeading() - calibrationPhi) >= 135)
                    && ((mobilePointTo.getHeading() - calibrationPhi) < 225)) {
                this.mobilePointsDiscrete.add(new MobilePointTo(mobilePointTo.getX(), mobilePointTo.getY(), mobilePointTo.getZ(),
                        Double.valueOf(180)));
            } else if (((mobilePointTo.getHeading() - calibrationPhi) >= 225)
                    || ((mobilePointTo.getHeading() - calibrationPhi) < 315)) {
                this.mobilePointsDiscrete.add(new MobilePointTo(mobilePointTo.getX(), mobilePointTo.getY(), mobilePointTo.getZ(),
                        Double.valueOf(270)));
            } else {
                this.mobilePointsDiscrete.add(new MobilePointTo(mobilePointTo.getX(), mobilePointTo.getY(), mobilePointTo.getZ(),
                        Double.valueOf(0)));
            }
        }
        System.out.println(this.mobilePointsDiscrete.size());
        for (MobilePointTo mobilePointTo : this.mobilePointsDiscrete) {
            System.out.println(mobilePointTo.getHeading());
            
        }
        assertSame("Test", -1, -1);
    }
}
