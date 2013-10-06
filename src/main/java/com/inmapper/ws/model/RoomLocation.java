package com.inmapper.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "room_location")
@XmlRootElement
public class RoomLocation {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "room_id", nullable = false)
    private String roomId;
    
    @Column(name = "mobile_id", nullable = false)
    private String mobileId;
    
    private RoomPoint point;
    
    RoomLocation() {
    }
    
    public RoomLocation(String roomId, String mobileId, RoomPoint point) {
        this.roomId = roomId;
        this.mobileId = mobileId;
        this.point = point;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getRoomId() {
        return this.roomId;
    }
    
    public String getMobileId() {
        return this.mobileId;
    }
    
    public RoomPoint getPoint() {
        return this.point;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
