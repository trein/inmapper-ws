package com.inmapper.ws.model.to;

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
