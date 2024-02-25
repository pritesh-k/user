package com.app.user.service;

import com.app.user.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Loads a user by their email address.
     *
     * @param emailAddress The email address of the user.
     * @return A UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Query query = new Query().addCriteria(Criteria.where("email").is(emailAddress));
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + emailAddress);
        }
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
