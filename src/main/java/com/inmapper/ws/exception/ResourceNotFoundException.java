package com.inmapper.ws.exception;

public class ResourceNotFoundException extends Exception {
    
    private static final long serialVersionUID = 8062306968958714655L;
    
    public ResourceNotFoundException(String id) {
	super(String.format("Resource with id [%s] not found.", id));
    }
    
}
