package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;



@RestController
public class UserController {
    @Autowired
    private UserService userService;
@PostMapping("/user-service")
public ResponseEntity<User> saveUser(@RequestBody User user) {
    return ResponseEntity.ok( userService.saveUser(user));
}

@GetMapping("/user-service")
public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
}
@GetMapping("/user-service/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
}



}
