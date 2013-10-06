package com.inmapper.ws.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomPoint {
    
    @Column(name = "angle", nullable = false)
    private Double angle;
    
    @Column(name = "x", nullable = false)
    private Double x;
    
    @Column(name = "y", nullable = false)
    private Double y;
    
    RoomPoint() {
    }
    
    public RoomPoint(Double angle, Double x, Double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }
    
    public Double getAngle() {
        return this.angle;
    }
    
    public Double getX() {
        return this.x;
    }
    
    public Double getY() {
        return this.y;
    }
    
}
