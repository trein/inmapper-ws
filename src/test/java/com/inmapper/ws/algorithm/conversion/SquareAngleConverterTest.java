package com.inmapper.ws.algorithm.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquareAngleConverterTest {
    
    private final SquareAngleConverter converter = new SquareAngleConverter();
    
    @Test
    public void shouldIgnoreVariationLessThan45() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi + 44;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 0, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThanNegative45() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi - 44;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 0, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThan90() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi + 88;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 90, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThanNegative90() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi - 88;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 270, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThan135() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi + 130;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 90, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThanNegative135() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi - 135;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 270, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThan225() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi + 220;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 180, theta, 0.1);
    }
    
    @Test
    public void shouldIgnoreVariationLessThanNegative225() {
        // given
        double calibrationPhi = 60;
        double previousTheta = 0;
        double heading = calibrationPhi - 220;
        
        // when
        double theta = this.converter.computeTheta(heading, calibrationPhi, previousTheta);
        
        // then
        assertEquals("Different theta values", 180, theta, 0.1);
    }
}
