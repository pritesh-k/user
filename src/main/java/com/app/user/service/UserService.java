package com.app.user.service;

import com.app.user.model.api.Login;
import com.app.user.model.data.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, String> loginUser(Login login) throws UsernameNotFoundException;
    void registerUser(User user) throws Exception;

    public List<User> listUsers(Integer limit, Integer offset);

    public Long countUsers();
}
