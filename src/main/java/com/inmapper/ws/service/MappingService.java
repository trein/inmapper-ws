package com.inmapper.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.inmapper.ws.exception.ResourceNotFoundException;
import com.inmapper.ws.model.Position;

@Path("/v")
public interface MappingService {
    
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
    Response positions(Position position);
    
    @GET
    @Path("/positions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response positionsId(@PathParam("id") String id) throws ResourceNotFoundException;
    
}
