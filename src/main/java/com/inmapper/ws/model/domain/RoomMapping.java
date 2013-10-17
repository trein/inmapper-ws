package com.inmapper.ws.model.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

@Entity
@Table(name = "room_mapping")
@XmlRootElement
public class RoomMapping {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "room_id", nullable = false)
    private String roomId;
    
    @MapKey(name = "mobileId")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomMapping")
    private Map<String, UserSession> sessions;
    
    RoomMapping() {
    }
    
    public RoomMapping(String roomId) {
        this.roomId = roomId;
        this.sessions = Maps.newHashMap();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getRoomId() {
        return this.roomId;
    }
    
    public Collection<UserSession> getSessions() {
        return Collections.unmodifiableCollection(this.sessions.values());
    }
    
    public void appendToSession(String mobileId, UserLocation location) {
        getSession(mobileId).addLocation(location);
    }
    
    public UserSession getSession(String mobileId) {
        UserSession userSession = this.sessions.get(mobileId);
        
        if (userSession == null) {
            userSession = new UserSession(this, mobileId);
        }
        return userSession;
    }
    
    public void addSession(UserSession session) {
        this.sessions.put(session.getMobileId(), session);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof RoomMapping) {
            RoomMapping other = (RoomMapping) o;
            
            return Objects.equal(this.id, other.id) && Objects.equal(this.roomId, other.roomId)
                    && Objects.equal(this.sessions, other.sessions);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.roomId, this.sessions);
    }
    
}
