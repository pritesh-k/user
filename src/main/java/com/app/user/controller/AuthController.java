package com.app.user.controller;

import com.app.user.model.api.Login;
import com.app.user.model.api.User;
import com.app.user.service.DummyUserMapper;
import com.app.user.service.UserService;
import com.app.user.util.UserModelMapper;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth/api/v1")
public class AuthController {
    @Autowired
    UserModelMapper userModelMapper;

    @Autowired
    UserService userService;

    @Autowired
    DummyUserMapper dummyUserMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> loginUser(@Valid Login login){
        try {
            if(login.getUsername() == null || login.getPassword() == null) {
                throw new Exception("UserName or Password is Empty");
            }
            Map<String, String> token = userService.loginUser(login);

            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody User user){
        try {
            com.app.user.model.data.User userEntity = userModelMapper.userDtoToEntity(user);
            // create user object
            userService.registerUser(userEntity);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/addDummyUsers")
    public ResponseEntity<Void> registerUser(){
        try {

            // create user object
            dummyUserMapper.mapToNewTest();

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
