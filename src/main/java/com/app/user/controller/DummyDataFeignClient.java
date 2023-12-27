package com.app.user.controller;

import com.app.user.model.externalModel.DummyUserParent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dummyData", url = "https://dummyjson.com/users?limit=100")
public interface DummyDataFeignClient {
    @GetMapping
    ResponseEntity<DummyUserParent> listUsers();
}
