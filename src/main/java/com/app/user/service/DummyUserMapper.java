package com.app.user.service;

import com.app.user.controller.DummyDataFeignClient;
import com.app.user.model.data.User;
import com.app.user.model.externalModel.DummyUser;
import com.app.user.model.externalModel.DummyUserParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DummyUserMapper {
    @Autowired
    DummyDataFeignClient feignClient;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Fetches dummy user data from an external source using FeignClient,
     * maps it to internal User entities, and saves them to MongoDB.
     */
    public void mapToNewTest(){

        ResponseEntity<DummyUserParent> responseEntity = feignClient.listUsers();
        List<DummyUser> list = responseEntity.getBody().getUsers();

        for (DummyUser user : list){
            User user1 = new User();
            user1.setId(UUID.randomUUID());
            user1.setDummyId(user.getId());

            user1.setFirstName( user.getFirstName() );
            user1.setLastName( user.getLastName() );
            user1.setMaidenName( user.getMaidenName() );
            user1.setAge( user.getAge() );
            user1.setGender( user.getGender() );
            user1.setEmail( user.getEmail() );
            user1.setPhone( user.getPhone() );
            user1.setUsername( user.getUsername() );
            user1.setPassword( passwordEncoder.encode("1234") );
            user1.setBirthDate( user.getBirthDate() );
            user1.setImage( user.getImage() );
            user1.setBloodGroup( user.getBloodGroup() );
            user1.setHeight( user.getHeight() );
            user1.setWeight( user.getWeight() );
            user1.setEyeColor( user.getEyeColor() );

            com.app.user.model.api.User.HairInfo hairInfo1 = new com.app.user.model.api.User.HairInfo();

            hairInfo1.setColor( user.getHair().getColor() );
            hairInfo1.setType( user.getHair().getType() );

            user1.setHair( hairInfo1 );
            user1.setDomain( user.getDomain() );
            user1.setIp( user.getIp() );
            user1.setAddress( user.getAddress() );
            user1.setMacAddress( user.getMacAddress() );
            user1.setUniversity( user.getUniversity() );
            user1.setBank( user.getBank() );
            user1.setCompany( user.getCompany() );
            user1.setEin( user.getEin() );
            user1.setSsn( user.getSsn() );
            user1.setUserAgent( user.getUserAgent() );
            user1.setCreatedDate(ZonedDateTime.now(ZoneOffset.UTC));

            mongoTemplate.save(user1);
        }
    }
}
