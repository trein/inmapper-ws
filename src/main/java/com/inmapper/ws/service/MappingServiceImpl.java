package com.inmapper.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.MobilePosition;
import com.inmapper.ws.model.RoomLocation;
import com.inmapper.ws.repository.RoomLocationRepository;

/**
 * Service implementation orchestrating mobile sensor data conversion and persistence.
 * 
 * @author trein
 */
@Service
@Transactional(readOnly = true)
public class MappingServiceImpl implements MappingService {
    
    private final RoomLocationRepository repository;
    private final MappingAlgorithm algorithm;
    
    @Autowired
    public MappingServiceImpl(MappingAlgorithm algorithm, RoomLocationRepository repository) {
        this.algorithm = algorithm;
        this.repository = repository;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Long handlePosition(MobilePosition position) throws InvalidMobilePositionException {
        RoomLocation location = this.algorithm.decodePosition(position);
        this.repository.save(location);
        
        return location.getId();
    }
    
    @Override
    public RoomLocation retrieveLocation(Long id) throws ResourceNotFoundException {
        RoomLocation location = this.repository.findOne(id);
        
        if (location == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return location;
    }
    
    @Override
    public List<RoomLocation> retrieveRoomLocations(String roomId) throws ResourceNotFoundException {
        List<RoomLocation> locations = this.repository.findAllByRoomId(roomId);
        
        if ((locations == null) || locations.isEmpty()) {
            throw new ResourceNotFoundException(roomId);
        }
        return locations;
    }
    
}
