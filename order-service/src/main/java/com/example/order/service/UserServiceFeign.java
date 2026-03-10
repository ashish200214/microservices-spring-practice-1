package com.example.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.dto.UserDTO;

@FeignClient(name = "user-service")
public interface UserServiceFeign {
    @GetMapping("/user-service/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id);
}
