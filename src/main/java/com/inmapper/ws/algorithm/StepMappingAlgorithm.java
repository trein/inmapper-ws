package com.inmapper.ws.algorithm;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

/**
 * Algorithm in charge of converting mobile sensor information into relative
 * indoor coordinates.
 * 
 * @author trein
 * @author navjot
 * @author priya
 */
@Component
public class StepMappingAlgorithm implements MappingAlgorithm {

    private static final double STEP_CONVERSION = 0.45;
    private static final double Z_THRESHOLD = 12;

    @Override
    public List<UserLocation> decodePosition(MobileSessionTo session) throws InvalidMobilePositionException {
        List<UserLocation> locations = Lists.newArrayList();
        List<MobilePointTo> stepsPoints = filterSteps(session.getPositions());
        double stepLength = computeStepLength(session);

        UserLocation previousLocation = UserLocation.zeroValue();
        Double calibrationPhi = stepsPoints.remove(0).getHeading();

        for (MobilePointTo point : stepsPoints) {
            double theta = point.getHeading().doubleValue() - calibrationPhi.doubleValue();
            double x = previousLocation.getX().doubleValue() + (stepLength * Math.cos(theta));
            double y = previousLocation.getY().doubleValue() + (stepLength * Math.sin(theta));

            locations.add(new UserLocation(Double.valueOf(x), Double.valueOf(y)));
        }
        return locations;
    }

    private double computeStepLength(MobileSessionTo session) {
        return Double.valueOf(session.getUserHeight()).doubleValue() * STEP_CONVERSION;
    }

    private List<MobilePointTo> filterSteps(Collection<MobilePointTo> positions) {
        List<MobilePointTo> filteredPoints = Lists.newArrayList();
        List<MobilePointTo> localPoints = Lists.newArrayList();

        for (MobilePointTo point : positions) {
            if (point.getZ().doubleValue() >= Z_THRESHOLD) {
                localPoints.add(point);
            } else if (!localPoints.isEmpty()) {
                MobilePointTo average = MobilePointTo.computeAverage(localPoints);
                filteredPoints.add(average);
                localPoints = Lists.newArrayList();
            }
        }

        if (!localPoints.isEmpty()) {
            MobilePointTo average = MobilePointTo.computeAverage(localPoints);
            filteredPoints.add(average);
        }
        return filteredPoints;
    }

}
