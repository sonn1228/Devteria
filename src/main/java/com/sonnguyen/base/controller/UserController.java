package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.request.ApiResponse;
import com.sonnguyen.base.dto.request.UserCreationRequest;
import com.sonnguyen.base.dto.request.UserUpdateRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @PutMapping("/{userId}")
    public User getUserById(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return this.userService.updateUser(userId, request);
    }
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return this.userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteById(@PathVariable String userId){
        userService.deleteById(userId);
        return "User has been deleted";
    }

}
