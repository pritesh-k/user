package com.app.user.service;

import com.app.user.model.api.Login;
import com.app.user.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    /**
     * Authenticates the user and generates a JWT token upon successful authentication.
     *
     * @param login The login credentials.
     * @return A Map containing the JWT token.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public Map<String, String> loginUser(Login login) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                login.getUsername(), login.getPassword()));

        if (authentication.isAuthenticated()) {
            User user = mongoTemplate.findOne(new Query(Criteria.where("email").is(login.getUsername())), User.class);
            return jwtService.generateToken(user);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    /**
     * Registers a new user.
     *
     * @param user The user information to be registered.
     * @throws Exception If the registration fails.
     */
    @Override
    public void registerUser(User user) throws Exception {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("Missing parameter emailAddress");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID());

        User registeredUser = mongoTemplate.save(user);
        if (registeredUser == null) {
            throw new Exception("Some Exception");
        }
    }

    /**
     * Lists users with pagination.
     *
     * @param limit  The maximum number of users to retrieve.
     * @param offset The starting index of users.
     * @return A list of User objects.
     */
    public List<User> listUsers(Integer limit, Integer offset) {
        Query query = new Query();

        query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
        query.limit(limit);
        query.skip(offset);
        query.addCriteria(Criteria.where("_class").is("com.app.user.model.data.User"));

        return mongoTemplate.find(query, User.class);
    }

    /**
     * Counts the total number of users.
     *
     * @return The total number of users.
     */
    public Long countUsers() {
        Query query = new Query();

        query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
        query.addCriteria(Criteria.where("_class").is("com.app.user.model.data.User"));

        return mongoTemplate.count(query, User.class);
    }
}
