package com.inmapper.ws.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inmapper.ws.model.domain.RoomMapping;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.domain.UserSession;
import com.inmapper.ws.repository.RoomMappingRepository;
import com.inmapper.ws.service.IdGenerator;

/**
 * Sample room locations for testing matters.
 * 
 * @author trein
 */
@Component
public class SampleRoomLocations {
    
    private static final String ROOM_ID = "sample_room";
    
    private final IdGenerator generator;
    private final RoomMappingRepository repository;
    
    @Autowired
    public SampleRoomLocations(RoomMappingRepository repository, IdGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }
    
    @PostConstruct
    @Transactional
    protected void createSampleData() {
        if (this.repository.findByRoomId(ROOM_ID) == null) {
            RoomMapping roomMapping = new RoomMapping(ROOM_ID);
            
            this.repository.save(createLocations(roomMapping));
            this.repository.save(createLocations(roomMapping));
        }
    }
    
    @SuppressWarnings("boxing")
    private RoomMapping createLocations(RoomMapping roomMapping) {
        String mobileId = this.generator.next();
        UserSession session = roomMapping.getSession(mobileId);
        
        for (int index = 0; index < 100; index++) {
            session.addLocation(new UserLocation(index + (30.0 * Math.random()), index + (20.0 * Math.random())));
        }
        roomMapping.addSession(session);
        
        return roomMapping;
    }
    
}
