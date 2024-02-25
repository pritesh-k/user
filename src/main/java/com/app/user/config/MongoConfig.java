package com.app.user.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;

/**
 * MongoDB configuration class for defining MongoClient, database factory, and custom converters.
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    ServerConfig serverConfig;

    /**
     * Retrieve the MongoDB database name.
     *
     * @return The MongoDB database name.
     */
    @Override
    protected String getDatabaseName() {
        return serverConfig.getMongoDbName();
    }

    /**
     * Configure and provide the MongoClient bean.
     *
     * @return The configured MongoClient.
     */
    @Override
    public MongoClient mongoClient() {
        String uri = serverConfig.getMongoUri();

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)));
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    /**
     * Configure and provide the MongoDatabaseFactory bean.
     *
     * @return The configured MongoDatabaseFactory.
     */
    @Override
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
    }

    /**
     * Configure and provide the MongoTemplate bean.
     *
     * @return The configured MongoTemplate.
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory(), mongoConverter());
    }

    /**
     * Custom converter for reading ZonedDateTime from Date.
     */
    @ReadingConverter
    public static class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(Date date) {
            return date.toInstant().atZone(ZoneOffset.UTC);
        }
    }

    /**
     * Custom converter for writing ZonedDateTime to Date.
     */
    @WritingConverter
    public static class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime zonedDateTime) {
            return Date.from(zonedDateTime.toInstant());
        }
    }

    // Uncomment and modify as needed for additional custom converters
    /*
    @ReadingConverter
    public static class UserRolesEnum implements Converter<String, UserDetails.RolesEnum> {
        @Override
        public UserDetails.RolesEnum convert(String role) {
            return UserDetails.RolesEnum.fromValue(role);
        }
    }
    */

    /**
     * Configure and provide custom conversions for MongoDB.
     *
     * @return The configured MongoCustomConversions.
     */
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()));
    }

    /**
     * Configure and provide the MongoConverter bean.
     *
     * @return The configured MongoConverter.
     */
    public MongoConverter mongoConverter() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver defaultDbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(defaultDbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        return mongoConverter;
    }
}
