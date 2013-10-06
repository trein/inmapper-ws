package com.inmapper.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.inmapper.ws.exception.InvalidMobilePositionException;
import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.MobilePosition;

@Path("/v")
public interface MappingFacade {
    
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response health();
    
    @GET
    @Path("/identification")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response identification();
    
    @POST
    @Path("/positions")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response positions(MobilePosition position) throws InvalidMobilePositionException;
    
    @GET
    @Path("/locations/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response locations(@PathParam("id") Long id) throws ResourceNotFoundException;
    
    @GET
    @Path("{room_id}/locations")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response roomLocations(@PathParam("room_id") String roomId) throws ResourceNotFoundException;
    
}
