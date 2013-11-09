package com.inmapper.ws.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@Entity
@Table(name = "user_location")
@XmlRootElement
public class UserLocation {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "x", nullable = false)
    private Double x;
    
    @Column(name = "y", nullable = false)
    private Double y;
    
    UserLocation() {
    }
    
    public UserLocation(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Double getX() {
        return this.x;
    }
    
    public Double getY() {
        return this.y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof UserLocation) {
            UserLocation other = (UserLocation) o;
            
            return Objects.equal(this.id, other.id) && Objects.equal(this.x, other.x) && Objects.equal(this.y, other.y);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.x, this.y);
    }
    
    @SuppressWarnings("boxing")
    public static UserLocation zeroValue() {
        return new UserLocation(0.0, 0.0);
    }
    
}
