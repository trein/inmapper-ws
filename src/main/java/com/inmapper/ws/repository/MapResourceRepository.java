package com.inmapper.ws.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.Position;

public class MapResourceRepository implements ResourceRepository {
    
    private Map<Double, Position> resources;
    
    @PostConstruct
    protected void init() {
        List<Position> sampleResources = Lists.newArrayList(new Position(1d, 2d, 3d, 4d), new Position(1d, 2d, 3d, 4d),
                new Position(4d, 5d, 6d, 7d), new Position(5d, 6d, 7d, 8d), new Position(8d, 3d, 2d, 1d));
        
        this.resources = Maps.uniqueIndex(sampleResources, new Function<Position, Double>() {
            @Override
            public Double apply(Position from) {
                return from.getHeading();
            }
        });
    }
    
    @Override
    public Position getById(String id) throws ResourceNotFoundException {
        if (this.resources.containsKey(id)) {
            return this.resources.get(id);
        }
        throw new ResourceNotFoundException(id);
    }
    
    @Override
    public List<Position> getAll() {
        return ImmutableList.copyOf(this.resources.values());
    }
    
}
