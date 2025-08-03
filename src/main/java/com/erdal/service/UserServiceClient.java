package com.erdal.service;

import com.erdal.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/api/users")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/api/users/username/{username}")
    UserDTO getUserByUsername(@PathVariable String username);
}
