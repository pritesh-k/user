package com.app.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config-server-mongo")
public class ServerConfig {

    @Value("${mongoUri}")
    private  String mongoUri;

    @Value("${jwtSecret}")
    private  String jwtSecret;

    @Value("${mongoDbName}")
    private  String mongoDbName;

    public  String getMongoUri() {
        return mongoUri;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public String getMongoDbName() {
        return mongoDbName;
    }
}
