package com.inmapper.ws.model.domain;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@Entity
@Table(name = "user_session")
@XmlRootElement
public class UserSession {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "mobile_id", nullable = false)
    private String mobileId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_mapping_id", nullable = false)
    private RoomMapping roomMapping;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<UserLocation> locations;
    
    UserSession() {
    }
    
    UserSession(RoomMapping roomMapping, String mobileId) {
        this.roomMapping = roomMapping;
        this.mobileId = mobileId;
        this.locations = Lists.newArrayList();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getMobileId() {
        return this.mobileId;
    }
    
    public Collection<UserLocation> getLocations() {
        return Collections.unmodifiableCollection(this.locations);
    }
    
    public void addLocation(UserLocation location) {
        this.locations.add(location);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof UserSession) {
            UserSession other = (UserSession) o;
            
            return Objects.equal(this.id, other.id) && Objects.equal(this.roomMapping.getId(), other.roomMapping.getId())
                    && Objects.equal(this.mobileId, other.mobileId) && Objects.equal(this.locations, other.locations);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.roomMapping.getId(), this.mobileId, this.locations);
    }
}
