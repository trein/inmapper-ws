package com.inmapper.ws.repository;

import java.util.List;

import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.Position;


/**
 * Repository interface for resources.
 * 
 * @author trein
 */
public interface ResourceRepository {
    
    /**
     * Retrieve a resource by its id.
     * 
     * @param id resource's unique identification.
     * @return found resource.
     * @throws ResourceNotFoundException exception thrown in case of no resource found.
     */
    Position getById(String id) throws ResourceNotFoundException;
    
    /**
     * Retrieve all resources stored in the repository.
     * 
     * @return all resources in the repository.
     */
    List<Position> getAll();
    
}
