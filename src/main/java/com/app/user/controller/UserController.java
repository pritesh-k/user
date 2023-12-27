package com.app.user.controller;

import com.app.user.model.api.Pagination;
import com.app.user.model.api.UserApiResponse;
import com.app.user.model.data.User;
import com.app.user.service.UserService;
import com.app.user.util.UserModelMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/api/v1")
public class UserController {
    @Autowired
    UserModelMapper userModelMapper;

    @Autowired
    UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/list")
    public ResponseEntity<UserApiResponse> listUsers(
            @RequestParam(value = "limit", defaultValue = "20") Integer limit,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ){
        try {

            List<User> userList = userService.listUsers(limit, offset);
            Long total = userService.countUsers();
            UserApiResponse userApiResponse = new UserApiResponse();
            userApiResponse.setPagination(new Pagination().limit(limit).offset(offset).total(total));

            for (User user : userList){
                com.app.user.model.api.User userModel = userModelMapper.userEntityToDto(user);
                userApiResponse.addResultsItem(userModel);
            }
            return new ResponseEntity<>(userApiResponse, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
