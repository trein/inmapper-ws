package com.inmapper.ws.algorithm.conversion;

import java.util.List;

import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

/**
 * Business logic in charge of converting raw data sent by mobile clients to indoor points.
 * 
 * @author trein
 */
public interface PointConverter {
    
    double STEP_CONVERSION = 0.45;
    
    /**
     * Conversion of raw data to indoor points.
     * 
     * @param session mobile client session information.
     * @param stepsPoints points detected as steps in mobile session.
     * @return set of indoor points.
     */
    List<UserLocation> convert(MobileSessionTo session, List<MobilePointTo> stepsPoints);
}
