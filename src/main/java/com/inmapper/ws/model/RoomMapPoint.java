package com.inmapper.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_point")
public class RoomMapPoint {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "room_id", nullable = false)
    private String roomId;
    
    @Column(name = "angle", nullable = false)
    private final String angle;
    
    @Column(name = "x", nullable = false)
    private final String x;
    
    @Column(name = "y", nullable = false)
    private final String y;
    
    public RoomMapPoint(String roomId, String angle, String x, String y) {
        this.roomId = roomId;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getRoomId() {
        return this.roomId;
    }
    
    public String getAngle() {
        return this.angle;
    }
    
    public String getX() {
        return this.x;
    }
    
    public String getY() {
        return this.y;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
