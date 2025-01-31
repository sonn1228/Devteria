package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.in.UserCreationRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public User createUser(UserCreationRequest request){
        return userService.createReq(request);
    }

}
