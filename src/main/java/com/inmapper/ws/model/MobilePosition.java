package com.inmapper.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement
public class MobilePosition {
    
    private Double x;
    private Double y;
    private Double z;
    private Double heading;
    
    MobilePosition() {
    }
    
    public MobilePosition(Double x, Double y, Double z, Double heading) {
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
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("x", this.x).add("y", this.y).add("z", this.z).add("heading", this.heading)
                .toString();
    }
    
}
