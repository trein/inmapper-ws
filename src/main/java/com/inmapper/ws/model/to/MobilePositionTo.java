package com.inmapper.ws.model.to;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement
public class MobilePositionTo {
    
    private String roomId;
    private String mobileId;
    private Double x;
    private Double y;
    private Double z;
    private Double heading;
    
    MobilePositionTo() {
    }
    
    public MobilePositionTo(String mobileId, String roomId, Double x, Double y, Double z, Double heading) {
        this.mobileId = mobileId;
        this.roomId = roomId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.heading = heading;
    }
    
    public String getRoomId() {
        return this.roomId;
    }
    
    public String getMobileId() {
        return this.mobileId;
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
        return Objects.toStringHelper(this).add("roomId", this.roomId).add("x", this.x).add("y", this.y).add("z", this.z).add(
                "heading", this.heading).toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof MobilePositionTo) {
            MobilePositionTo other = (MobilePositionTo) o;
            
            return Objects.equal(this.roomId, other.roomId) && Objects.equal(this.mobileId, other.mobileId)
                    && Objects.equal(this.x, other.x) && Objects.equal(this.y, other.y) && Objects.equal(this.z, other.z)
                    && Objects.equal(this.heading, other.heading);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.roomId, this.mobileId, this.x, this.y, this.z, this.heading);
    }
    
}
