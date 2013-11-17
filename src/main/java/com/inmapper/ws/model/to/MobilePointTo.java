package com.inmapper.ws.model.to;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.base.Objects;

@XmlRootElement
public class MobilePointTo {
    
    private Double x;
    private Double y;
    private Double z;
    private Double heading;
    
    MobilePointTo() {
    }
    
    public MobilePointTo(Double x, Double y, Double z, Double heading) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.heading = heading;
    }
    
    public Double getX() {
        return this.x;
    }
    
    public Double getY() {
        return this.y;
    }
    
    public Double getZ() {
        return this.z;
    }
    
    public Double getHeading() {
        return this.heading;
    }
    
    /*
     * Whatever sensors you use, the X, Y, Z that you're measuring will depend on the orientation of
     * the device. However, for detecting the activities that you mention, the result can't depend
     * on e.g. whether the user is holding the device in a portrait or landscape position, or
     * whether the device is flat or vertical, so the individual values of X, Y and Z won't be any
     * use. Instead you'll have to look at the length of the vector, i.e. sqrt(X*X+Y*Y+Z*Z) which is
     * independent of the device orientation.
     */
    @JsonIgnore
    public Double getVariation() {
        return Double.valueOf(Math.sqrt((this.x.doubleValue() * this.x.doubleValue())
                + (this.y.doubleValue() * this.y.doubleValue()) + (this.z.doubleValue() * this.z.doubleValue())));
        
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("x", this.x).add("y", this.y).add("z", this.z).add("heading", this.heading)
                .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof MobilePointTo) {
            MobilePointTo other = (MobilePointTo) o;
            
            return Objects.equal(this.x, other.x) && Objects.equal(this.y, other.y) && Objects.equal(this.z, other.z)
                    && Objects.equal(this.heading, other.heading);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.x, this.y, this.z, this.heading);
    }
    
    public static Double computeAverage(Collection<MobilePointTo> points) {
        Double average = Double.valueOf(0);
        double variationSum = 0;
        int examples = points.size();
        
        if (!points.isEmpty()) {
            for (MobilePointTo point : points) {
                variationSum += point.getVariation().doubleValue();
            }
            average = Double.valueOf(variationSum / examples);
        }
        return average;
    }
    
    public static MobilePointTo getMedian(List<MobilePointTo> window) {
        return window.get(window.size() / 2);
    }
    
    public static Double computeStandardDeviation(List<MobilePointTo> points) {
        double average = computeAverage(points).doubleValue();
        double variationSum = 0.0;
        
        for (MobilePointTo point : points) {
            variationSum += Math.pow((average - point.getVariation().doubleValue()), 2);
        }
        return Double.valueOf(Math.sqrt(variationSum / points.size()));
    }
    
}
