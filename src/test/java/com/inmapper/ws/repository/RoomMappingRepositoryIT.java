package com.inmapper.ws.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.inmapper.ws.model.domain.RoomMapping;
import com.inmapper.ws.model.domain.UserLocation;
import com.inmapper.ws.model.domain.UserSession;

/**
 * Integration test for {@link RoomMappingRepository}.
 * 
 * @author trein
 */
@SuppressWarnings("boxing")
public class RoomMappingRepositoryIT extends BaseRepositoryIT<RoomMappingRepository> {
    
    public RoomMappingRepositoryIT() {
        super(RoomMappingRepository.class);
    }
    
    @Test
    public void shouldPersistNewMappings() {
        // given
        RoomMapping mapping = new RoomMapping("hall");
        UserSession userSession = mapping.getSession("6c637ce7-23ca-4f95-bb44-0419090d0337");
        
        userSession.addLocations(new UserLocation(1.0, 2.0));
        
        // when
        getRepository().saveAndFlush(mapping);
        
        List<RoomMapping> mappings = getRepository().findAll();
        RoomMapping foundMapping = getRepository().findOne(mapping.getId());
        
        // then
        assertThat(mappings, hasItem(mapping));
        assertThat(foundMapping, is(mapping));
    }
    
    @Test
    public void shouldPersistNewMappingsAndPropagateChanges() {
        // given
        String mobileId = "6c637ce7-23ca-4f95-bb44-0419090d0337";
        UserLocation location1 = new UserLocation(1.0, 2.0);
        UserLocation location2 = new UserLocation(9.0, 6.0);
        
        RoomMapping mapping = new RoomMapping("hall");
        mapping.getSession(mobileId).addLocations(location1);
        getRepository().saveAndFlush(mapping);
        Collection<UserLocation> expectedLocations = mapping.getSession(mobileId).getLocations();
        
        // when
        RoomMapping foundMapping = getRepository().findOne(mapping.getId());
        foundMapping.getSession(mobileId).addLocations(location2);
        getRepository().save(foundMapping);
        
        RoomMapping propagatedPosition = getRepository().findOne(mapping.getId());
        Collection<UserLocation> actualLocations = propagatedPosition.getSession(mobileId).getLocations();
        
        // then
        assertThat(propagatedPosition.getRoomId(), is(mapping.getRoomId()));
        assertThat(propagatedPosition.getSession(mobileId), is(mapping.getSession(mobileId)));
        assertThat(actualLocations, hasItems(expectedLocations.toArray(new UserLocation[0])));
    }
}
