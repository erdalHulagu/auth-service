package com.erdal.service;

import com.erdal.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")  // user-service, Eureka'daki servis adıyla aynı olmalı
public interface UserServiceClient {

    @GetMapping("/api/users/{id}")  // user-service'deki kullanıcıyı getiren endpoint
    UserDTO getUserById(@PathVariable Long id);
}
