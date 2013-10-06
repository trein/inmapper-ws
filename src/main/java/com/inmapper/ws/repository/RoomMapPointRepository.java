package com.inmapper.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inmapper.ws.model.RoomMapPoint;

/**
 * Room Map Point repository. The implementation of this class will be automatically generated by
 * Spring Data framework.
 * 
 * @author trein
 */
@Repository
public interface RoomMapPointRepository extends JpaRepository<RoomMapPoint, Long> {
    
}
