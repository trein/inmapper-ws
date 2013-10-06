package com.inmapper.ws.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.inmapper.ws.model.RoomLocation;
import com.inmapper.ws.model.RoomPoint;

/**
 * Integration test for {@link RoomLocationRepository}.
 * 
 * @author trein
 */
@SuppressWarnings("boxing")
public class RoomLocationRepositoryIT extends BaseRepositoryIT<RoomLocationRepository> {
    
    public RoomLocationRepositoryIT() {
        super(RoomLocationRepository.class);
    }
    
    @Test
    public void shouldPersistNewLocations() {
        // given
        RoomPoint point = new RoomPoint(180.0, 1.0, 2.0);
        RoomLocation position = new RoomLocation("hall", "6c637ce7-23ca-4f95-bb44-0419090d0337", point);
        
        // when
        getRepository().saveAndFlush(position);
        
        List<RoomLocation> positions = getRepository().findAll();
        RoomLocation foundPosition = getRepository().findOne(position.getId());
        
        // then
        assertThat(positions, hasItem(position));
        assertThat(foundPosition, is(position));
    }
    
    @Test
    public void shouldPersistNewLocationAndPropagateChanges() {
        // given
        RoomPoint point = new RoomPoint(180.0, 1.0, 2.0);
        RoomLocation position = new RoomLocation("hall", "6c637ce7-23ca-4f95-bb44-0419090d0337", point);
        
        // when
        getRepository().saveAndFlush(position);
        
        RoomLocation foundPosition = getRepository().findOne(position.getId());
        position.setRoomId("hall2");
        RoomLocation propagatedPosition = getRepository().save(position);
        
        // then
        assertThat(foundPosition.getRoomId(), is(position.getRoomId()));
        assertThat(propagatedPosition.getRoomId(), is(position.getRoomId()));
    }
}
