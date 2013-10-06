package com.inmapper.ws.service;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    
    private static final double MIN = 1111111;
    private static final double MAX = 99999999;
    
    public Double next() {
        return Double.valueOf(MIN + (Math.random() * (MAX - MIN)));
    }
    
}
