package com.inmapper.ws.model.to;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

@XmlRootElement
public class MobileSessionTo {
    
    private String token;
    private String roomId;
    private String userHeight;
    private List<MobilePointTo> positions;
    
    MobileSessionTo() {
    }
    
    public MobileSessionTo(String token, String roomId, String userHeight, List<MobilePointTo> positions) {
        this.token = token;
        this.roomId = roomId;
        this.userHeight = userHeight;
        this.positions = positions;
    }
    
    public String getRoomId() {
        return this.roomId;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public String getUserHeight() {
        return this.userHeight;
    }
    
    public List<MobilePointTo> getPositions() {
        return this.positions;
    }
    
    @JsonIgnore
    public boolean isValid() {
        return !Strings.isNullOrEmpty(this.token) && !Strings.isNullOrEmpty(this.roomId)
                && !Strings.isNullOrEmpty(this.userHeight) && (this.positions != null) && !this.positions.isEmpty();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("token", this.token).add("roomId", this.roomId)
                .add("userHeight", this.userHeight).add("positions", this.positions).toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof MobileSessionTo) {
            MobileSessionTo other = (MobileSessionTo) o;
            
            return Objects.equal(this.token, other.token) && Objects.equal(this.roomId, other.roomId)
                    && Objects.equal(this.userHeight, other.userHeight) && Objects.equal(this.positions, other.positions);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.token, this.roomId, this.userHeight, this.positions);
    }
    
}
