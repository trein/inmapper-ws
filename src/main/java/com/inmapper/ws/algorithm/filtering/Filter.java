package com.inmapper.ws.algorithm.filtering;

import java.util.Collection;
import java.util.List;

import com.inmapper.ws.model.to.MobilePointTo;

public interface Filter {
    
    List<MobilePointTo> filter(Collection<MobilePointTo> points);
    
}
