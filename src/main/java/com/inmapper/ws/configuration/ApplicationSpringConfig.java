package com.inmapper.ws.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.inmapper.ws.repository.H2FileEmbeddedDatabaseConfigurer;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.inmapper")
@ComponentScan(basePackages = { "com.inmapper" })
public class ApplicationSpringConfig {
    
    private static final String DATABASE_NAME = "./db/inmapper_db";
    
    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
        
        factory.setDatabasePopulator(new ResourceDatabasePopulator());
        factory.setDatabaseName(DATABASE_NAME);
        factory.setDatabaseConfigurer(H2FileEmbeddedDatabaseConfigurer.getInstance());
        
        return factory.getDatabase();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException, ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        
        /**
         * Renamed to jpa-persistence.xml cause Spring will manage the DataSource and not the
         * container.
         */
        entityManagerFactoryBean.setPersistenceXmlLocation("classpath*:META-INF/jpa-persistence.xml");
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        
        return entityManagerFactoryBean;
    }
    
    private Properties getJpaProperties() {
        Properties jpaProperties = new Properties();
        
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.show_sql", "false");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.dialect", H2Dialect.class.getName());
        jpaProperties.put("hibernate.cache.region.factory_class", "net.sf.ehcache.hibernate.SingletonEhCacheRegionFactory");
        jpaProperties.put("hibernate.cache.use_second_level_cache", "true");
        jpaProperties.put("hibernate.cache.use_query_cache", "true");
        
        return jpaProperties;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException, ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        
        transactionManager.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
        
        return transactionManager;
    }
}
