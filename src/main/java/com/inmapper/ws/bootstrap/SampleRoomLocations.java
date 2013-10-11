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
    private static final String MOBILE_ID_1 = "6c637ce7-23ca-4f95-bb44-0419090d0337";
    private static final String MOBILE_ID_2 = "f3901ce7-45df-4e9e-ba40-5134056d0933";
    
    private final RoomLocationRepository repository;
    
    @Autowired
    public SampleRoomLocations(RoomLocationRepository repository) {
        this.repository = repository;
    }
    
    @PostConstruct
    @Transactional
    protected void createSampleData() {
        if (this.repository.findAllByRoomId(ROOM_ID).isEmpty()) {
            this.repository.save(createM1Locations());
            this.repository.save(createM2Locations());
        }
    }
    
    @SuppressWarnings("boxing")
    private List<RoomLocation> createM1Locations() {
        List<RoomLocation> locations = Lists.newArrayList();
        
        for (int index = 0; index < 100; index++) {
            RoomPoint point = new RoomPoint(index + 3.0, index + 2.0);
            RoomLocation location = new RoomLocation(ROOM_ID, MOBILE_ID_1, point);
            
            locations.add(location);
        }
        return locations;
    }
    
    @SuppressWarnings("boxing")
    private List<RoomLocation> createM2Locations() {
        List<RoomLocation> locations = Lists.newArrayList();
        
        for (int index = 0; index < 100; index++) {
            RoomPoint point = new RoomPoint(index * 3.0, index * 2.0);
            RoomLocation location = new RoomLocation(ROOM_ID, MOBILE_ID_2, point);
            
            locations.add(location);
        }
        return locations;
    }
}
