package com.inmapper.ws.bootstrap;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.inmapper.ws.model.RoomLocation;
import com.inmapper.ws.model.RoomPoint;
import com.inmapper.ws.repository.RoomLocationRepository;

/**
 * Sample room locations for testing matters.
 * 
 * @author trein
 */
@Component
public class SampleRoomLocations {
    
    private static final String ROOM_ID = "sample_room";
    private static final String MOBILE_ID = "6c637ce7-23ca-4f95-bb44-0419090d0337";
    
    private final RoomLocationRepository repository;
    
    @Autowired
    public SampleRoomLocations(RoomLocationRepository repository) {
        this.repository = repository;
    }
    
    @PostConstruct
    @Transactional
    protected void createSampleData() {
        if (this.repository.findAllByRoomId(ROOM_ID).isEmpty()) {
            this.repository.save(createLocations());
        }
    }
    
    @SuppressWarnings("boxing")
    private List<RoomLocation> createLocations() {
        List<RoomLocation> locations = Lists.newArrayList();
        
        for (int index = 0; index < 100; index++) {
            RoomPoint point = new RoomPoint(0.0, 3.0, index + 2.0);
            RoomLocation location = new RoomLocation(ROOM_ID, MOBILE_ID, point);
            
            locations.add(location);
        }
        return locations;
    }
}
