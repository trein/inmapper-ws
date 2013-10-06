package com.inmapper.ws.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.inmapper.ws.model.RoomMapPoint;
import com.inmapper.ws.repository.RoomMapPointRepository;

public class RoomMapPointDaoIT extends BaseRepositoryIT<RoomMapPointRepository> {
    
    public RoomMapPointDaoIT() {
        super(RoomMapPointRepository.class);
    }
    
    @Test
    public void shouldPersistNewProducts() {
        // given
        RoomMapPoint position = new RoomMapPoint("hall", "123", "34", "45");
        
        // when
        getRepository().saveAndFlush(position);
        
        List<RoomMapPoint> positions = getRepository().findAll();
        RoomMapPoint foundPosition = getRepository().findOne(position.getId());
        
        // then
        assertThat(positions, hasItem(position));
        assertThat(foundPosition, is(position));
    }
    
    @Test
    public void shouldPersistNewProductAndPropagateChanges() {
        // given
        RoomMapPoint position = new RoomMapPoint("hall", "123", "34", "45");
        
        // when
        getRepository().saveAndFlush(position);
        
        RoomMapPoint foundPosition = getRepository().findOne(position.getId());
        position.setRoomId("new_description");
        RoomMapPoint propagatedPosition = getRepository().save(position);
        
        // then
        assertThat(foundPosition.getRoomId(), is(position.getRoomId()));
        assertThat(propagatedPosition.getRoomId(), is(position.getRoomId()));
    }
}
