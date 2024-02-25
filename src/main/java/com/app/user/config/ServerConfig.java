package com.app.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for server-related properties, retrieved from the configuration server.
 */
@Configuration
@ConfigurationProperties("config-server-mongo")
public class ServerConfig {

    // MongoDB connection URI
    @Value("${mongoUri}")
    private  String mongoUri;

    // JWT secret key for authentication
    @Value("${jwtSecret}")
    private  String jwtSecret;

    // MongoDB database name
    @Value("${mongoDbName}")
    private  String mongoDbName;

    /**
     * Get the MongoDB connection URI.
     *
     * @return The MongoDB connection URI.
     */
    public  String getMongoUri() {
        return mongoUri;
    }

    /**
     * Get the JWT secret key for authentication.
     *
     * @return The JWT secret key.
     */
    public String getJwtSecret() {
        return jwtSecret;
    }

    /**
     * Get the MongoDB database name.
     *
     * @return The MongoDB database name.
     */
    public String getMongoDbName() {
        return mongoDbName;
    }
}