package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.request.ApiResponse;
import com.sonnguyen.base.dto.request.UserCreationRequest;
import com.sonnguyen.base.dto.request.UserUpdateRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody @Valid UserCreationRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "User created successfully", user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User has been deleted", null));
    }
}
