package com.inmapper.ws.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;
import org.springframework.util.ClassUtils;

public class H2FileEmbeddedDatabaseConfigurer implements EmbeddedDatabaseConfigurer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(H2FileEmbeddedDatabaseConfigurer.class);
    
    private static H2FileEmbeddedDatabaseConfigurer INSTANCE;
    
    private final Class<? extends Driver> driverClass;
    
    /**
     * Get the singleton {@link H2FileEmbeddedDatabaseConfigurer} instance.
     * 
     * @return the configurer
     * @throws ClassNotFoundException if H2 is not on the classpath
     */
    @SuppressWarnings("unchecked")
    public static synchronized H2FileEmbeddedDatabaseConfigurer getInstance() throws ClassNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new H2FileEmbeddedDatabaseConfigurer((Class<? extends Driver>) ClassUtils.forName("org.h2.Driver",
                    H2FileEmbeddedDatabaseConfigurer.class.getClassLoader()));
        }
        return INSTANCE;
    }
    
    private H2FileEmbeddedDatabaseConfigurer(Class<? extends Driver> driverClass) {
        this.driverClass = driverClass;
    }
    
    @Override
    public void configureConnectionProperties(ConnectionProperties properties, String databaseLocation) {
        properties.setDriverClass(this.driverClass);
        properties.setUrl(String.format("jdbc:h2:%s;DB_CLOSE_DELAY=-1", databaseLocation));
        properties.setUsername("sa");
        properties.setPassword("");
    }
    
    @Override
    public void shutdown(DataSource dataSource, String databaseName) {
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("SHUTDOWN");
        } catch (SQLException ex) {
            LOGGER.warn("Could not shutdown embedded database", ex);
        }
    }
    
}
