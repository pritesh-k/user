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

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    ServerConfig serverConfig;

    @Override
    protected String getDatabaseName() {
        return serverConfig.getMongoDbName();
    }

    @Override
    public MongoClient mongoClient(){

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

    @Override
    public MongoDatabaseFactory mongoDbFactory(){
        return new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory(), mongoConverter());
    }

    @ReadingConverter
    public static class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime>{
        @Override
        public ZonedDateTime convert(Date date) {
            return date.toInstant().atZone(ZoneOffset.UTC);
        }
    }

    @WritingConverter
    public static class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date>{
        @Override
        public Date convert(ZonedDateTime zonedDateTime) {
            return Date.from(zonedDateTime.toInstant());
        }
    }

//    @ReadingConverter
//    public static class UserRolesEnum implements Converter<String, UserDetails.RolesEnum>{
//
//        @Override
//        public UserDetails.RolesEnum convert(String role) {
//            return UserDetails.RolesEnum.fromValue(role);
//        }
//    }

    public MongoCustomConversions customConversions(){
        return new MongoCustomConversions(Arrays.asList(new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()));
    }


    public MongoConverter mongoConverter(){
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver defaultDbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(defaultDbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        return mongoConverter;
    }
}
