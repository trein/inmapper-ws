package com.inmapper.ws.service;

import javax.ws.rs.Consumes;
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
import com.inmapper.ws.model.to.MobileSessionTo;

@Path("/v")
public interface MappingRESTFacade {
    
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response health();
    
    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response token();
    
    @POST
    @Path("/positions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response positions(MobileSessionTo position) throws InvalidMobilePositionException;
    
    @GET
    @Path("/convert/{operation}/{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response convert(@PathParam("operation") String operation, @PathParam("filename") String filename)
            throws InvalidMobilePositionException;
    
    @GET
    @Path("{room_id}/mappings")
    @Produces(MediaType.APPLICATION_JSON)
    @GZIP
    Response mappings(@PathParam("room_id") String roomId) throws ResourceNotFoundException;
    
}
