package com.inmapper.ws.model.to;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement
public class RoomMappingTo {
    
    private Map<String, Collection<RoomPointTo>> userMappings;
    
    RoomMappingTo() {
    }
    
    public RoomMappingTo(Map<String, Collection<RoomPointTo>> map) {
        this.userMappings = map;
    }
    
    public Map<String, Collection<RoomPointTo>> getUserMappings() {
        return this.userMappings;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("mappings", this.userMappings).toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof RoomMappingTo) {
            RoomMappingTo other = (RoomMappingTo) o;
            
            return Objects.equal(this.userMappings, other.userMappings);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.userMappings);
    }
    
}
