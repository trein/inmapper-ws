package com.inmapper.ws.model.to;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement
public class RoomPointTo {
    
    private String roomId;
    private String mobileId;
    private Double x;
    private Double y;
    
    RoomPointTo() {
    }
    
    public RoomPointTo(String roomId, String mobileId, Double x, Double y) {
        this.roomId = roomId;
        this.mobileId = mobileId;
        this.x = x;
        this.y = y;
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
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", this.mobileId).add("room", this.roomId).add("x", this.x).add("y", this.y)
                .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof RoomPointTo) {
            RoomPointTo other = (RoomPointTo) o;
            
            return Objects.equal(this.roomId, other.roomId) && Objects.equal(this.mobileId, other.mobileId)
                    && Objects.equal(this.x, other.x) && Objects.equal(this.y, other.y);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.roomId, this.mobileId, this.x, this.y);
    }
}
