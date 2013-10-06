package com.inmapper.ws.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple test for {@link IdGenerator} implementation.
 * 
 * @author trein
 */
public class IdGeneratorTest {
    
    private IdGenerator subject;
    
    @Before
    public void setup() {
        this.subject = new IdGenerator();
    }
    
    @Test
    public void shouldGenerateUniqueIds() {
        // given a generator
        
        // when
        String id1 = this.subject.next();
        String id2 = this.subject.next();
        
        // then
        assertThat(id1, is(not(id2)));
    }
    
}
