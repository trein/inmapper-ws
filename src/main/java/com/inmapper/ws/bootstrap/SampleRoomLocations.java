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
        RoomMapping roomMapping = this.repository.findByRoomId(ROOM_ID);
        
        if (roomMapping == null) {
            roomMapping = new RoomMapping(ROOM_ID);
            roomMapping.removeAllSessions();
            roomMapping.addSession(strangePattern(roomMapping));
            roomMapping.addSession(verticalPattern(roomMapping));
            roomMapping.addSession(horizontalPattern(roomMapping));
            
            this.repository.save(roomMapping);
        }
    }
    
    @SuppressWarnings("boxing")
    private UserSession strangePattern(RoomMapping roomMapping) {
        String mobileId = this.generator.next();
        UserSession session = roomMapping.getSession(mobileId);
        
        for (int index = 0; index < 100; index++) {
            int initialPos = 5;
            UserLocation location1 = new UserLocation(initialPos + createBias(), initialPos + index + createBias());
            UserLocation location2 = new UserLocation((initialPos * 2) + createBias(), initialPos + index + createBias());
            UserLocation location3 = new UserLocation((initialPos * 3) + createBias(), initialPos + index + createBias());
            UserLocation location4 = new UserLocation((initialPos * 3) + createBias(), initialPos + index + createBias());
            
            session.addLocations(location1);
            session.addLocations(location2);
            session.addLocations(location3);
            session.addLocations(location4);
            
            UserLocation location5 = new UserLocation(initialPos + index + createBias(), (initialPos * 1) + createBias());
            UserLocation location6 = new UserLocation(initialPos + index + createBias(), (initialPos * 2) + createBias());
            UserLocation location7 = new UserLocation(initialPos + index + createBias(), (initialPos * 3) + createBias());
            UserLocation location8 = new UserLocation(initialPos + index + createBias(), (initialPos * 4) + createBias());
            UserLocation location9 = new UserLocation(initialPos + index + createBias(), (initialPos * 5) + createBias());
            
            session.addLocations(location5);
            session.addLocations(location6);
            session.addLocations(location7);
            session.addLocations(location8);
            session.addLocations(location9);
        }
        return session;
    }
    
    @SuppressWarnings("boxing")
    private UserSession verticalPattern(RoomMapping roomMapping) {
        String mobileId = this.generator.next();
        UserSession session = roomMapping.getSession(mobileId);
        
        for (int index = 0; index < 60; index++) {
            int initialPos = 30;
            int step = 5;
            UserLocation location1 = new UserLocation(initialPos + createBias(), initialPos + index + createBias());
            UserLocation location2 = new UserLocation(initialPos + (step * 1) + createBias(), initialPos + index + createBias());
            UserLocation location3 = new UserLocation(initialPos + (step * 2) + createBias(), initialPos + index + createBias());
            UserLocation location4 = new UserLocation(initialPos + (step * 3) + createBias(), initialPos + index + createBias());
            
            session.addLocations(location1);
            session.addLocations(location2);
            session.addLocations(location3);
            session.addLocations(location4);
            
            UserLocation location5 = new UserLocation(initialPos + (step * 4) + createBias(), initialPos + index + createBias());
            UserLocation location6 = new UserLocation(initialPos + (step * 5) + createBias(), initialPos + index + createBias());
            UserLocation location7 = new UserLocation(initialPos + (step * 6) + createBias(), initialPos + index + createBias());
            UserLocation location8 = new UserLocation(initialPos + (step * 7) + createBias(), initialPos + index + createBias());
            UserLocation location9 = new UserLocation(initialPos + (step * 8) + createBias(), initialPos + index + createBias());
            
            session.addLocations(location5);
            session.addLocations(location6);
            session.addLocations(location7);
            session.addLocations(location8);
            session.addLocations(location9);
        }
        return session;
    }
    
    @SuppressWarnings("boxing")
    private UserSession horizontalPattern(RoomMapping roomMapping) {
        String mobileId = this.generator.next();
        UserSession session = roomMapping.getSession(mobileId);
        
        for (int index = 0; index < 80; index++) {
            int initialYPos = 70;
            int initialXPos = 20;
            int step = 5;
            UserLocation location2 = new UserLocation(initialXPos + index + createBias(), initialYPos + (step * 1) + createBias());
            UserLocation location3 = new UserLocation(initialXPos + index + createBias(), initialYPos + (step * 2) + createBias());
            UserLocation location4 = new UserLocation(initialXPos + index + createBias(), initialYPos + (step * 3) + createBias());
            UserLocation location5 = new UserLocation(initialXPos + index + createBias(), initialYPos + (step * 4) + createBias());
            
            session.addLocations(location2);
            session.addLocations(location3);
            session.addLocations(location4);
            session.addLocations(location5);
        }
        return session;
    }
    
    private double createBias() {
        return 5.0 * Math.random();
    }
    
}
