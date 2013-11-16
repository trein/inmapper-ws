package com.inmapper.ws.service;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.inmapper.ws.model.to.MobileSessionTo;

@Component
public class SessionAuditor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionAuditor.class);

    private static final String AUDIT_DIR_PATTERN = "." + File.separatorChar + "audit" + File.separatorChar + "%s";
    private final ResteasyJacksonProvider jacksonProvider;

    public SessionAuditor() {
        this.jacksonProvider = new ResteasyJacksonProvider();
    }

    public void saveSession(MobileSessionTo session) {
        ObjectMapper mapper = this.jacksonProvider.locateMapper(MobileSessionTo.class, MediaType.APPLICATION_JSON_TYPE);

        try {
            mapper.writeValue(new File(getAuditFilename(createNewWith(session.getToken()))), session);
        } catch (JsonGenerationException e) {
            LOGGER.error("Error saving session object as JSON in a file", e);
        } catch (JsonMappingException e) {
            LOGGER.error("Error saving session object as JSON in a file", e);
        } catch (IOException e) {
            LOGGER.error("Error saving session object as JSON in a file", e);
        }
    }

    public MobileSessionTo loadSession(String token) {
        ObjectMapper mapper = this.jacksonProvider.locateMapper(MobileSessionTo.class, MediaType.APPLICATION_JSON_TYPE);
        MobileSessionTo value = null;

        try {
            value = mapper.readValue(new File(getAuditFilename(token)), MobileSessionTo.class);
        } catch (JsonParseException e) {
            LOGGER.error("Error loading session object as JSON in a file", e);
        } catch (JsonMappingException e) {
            LOGGER.error("Error loading session object as JSON in a file", e);
        } catch (IOException e) {
            LOGGER.error("Error loading session object as JSON in a file", e);
        }
        return value;
    }

    private String createNewWith(String token) {
        return String.format("%s-%s", String.valueOf(System.currentTimeMillis()), token);
    }

    private String getAuditFilename(String file) {
        return String.format(AUDIT_DIR_PATTERN, file);
    }
}
