package com.inmapper.ws.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.inmapper.ws.configuration.ApplicationSpringConfig;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationSpringConfig.class)
public abstract class BaseRepositoryIT<R> {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    private final Class<R> repositoryType;
    
    public BaseRepositoryIT(Class<R> repositoryType) {
        this.repositoryType = repositoryType;
    }
    
    public R getRepository() {
        return this.applicationContext.getBean(this.repositoryType);
    }
    
    @Test
    public void shouldInjectOccurSuccessfully() {
        assertThat(getRepository(), is(notNullValue()));
    }
    
}
