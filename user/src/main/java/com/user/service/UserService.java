package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;

public User saveUser(User user){
    userRepository.save(user);
    return user;

}

public List<User> getAllUsers() {
    return userRepository.findAll();
}

public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(()->new RuntimeException("User with ID"+id+" doesn't exist"));
}
}
