package com.inmapper.ws.model.to;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

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
    
    @SuppressWarnings("boxing")
    public static MobilePointTo computeAverage(List<MobilePointTo> points) {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;
        double sumHeading = 0;
        int samples = points.size();
        
        for (MobilePointTo point : points) {
            sumX += point.x.doubleValue();
            sumY += point.y.doubleValue();
            sumZ += point.z.doubleValue();
            sumHeading += point.heading.doubleValue();
        }
        return new MobilePointTo(sumX / samples, sumY / samples, sumZ / samples, sumHeading / samples);
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
    
}
