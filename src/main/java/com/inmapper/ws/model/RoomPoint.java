package com.inmapper.ws.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomPoint {
    
    @Column(name = "x", nullable = false)
    private Double x;
    
    @Column(name = "y", nullable = false)
    private Double y;
    
    RoomPoint() {
    }
    
    public RoomPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
    
    public Double getX() {
        return this.x;
    }
    
    public Double getY() {
        return this.y;
    }
    
}
