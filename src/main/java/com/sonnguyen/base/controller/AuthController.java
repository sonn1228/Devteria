package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.out.AuthResponse;
import com.sonnguyen.base.dto.out.IntrospectResponse;
import com.sonnguyen.base.dto.request.ApiResponse;
import com.sonnguyen.base.dto.request.AuthRequest;
import com.sonnguyen.base.dto.request.IntrospectRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) {
        boolean isAuthenticated = authService.authenticate(request);

        if (isAuthenticated) {
            User user = authService.getUserByUsername(request.getUsername());
            String token = jwtService.generateToken(user.getUsername());

            return ApiResponse.<AuthResponse>builder()
                    .success(true)
                    .message("Login successful")
                    .result(AuthResponse.builder()
                            .authenticated(true)
                            .token(token)
                            .build())
                    .build();
        }

        return ApiResponse.<AuthResponse>builder()
                .success(false)
                .message("Invalid credentials")
                .result(AuthResponse.builder()
                        .authenticated(false)
                        .build())
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        boolean isValid = jwtService.validateToken(request);
        return ApiResponse.<IntrospectResponse>builder()
                .success(isValid)
                .message(isValid ? "Token is valid" : "Token is invalid")
                .result(IntrospectResponse.builder()
                        .valid(isValid)
                        .build())
                .build();
    }
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody AuthRequest request) {
        boolean isRegistered = authService.register(request);
        return ApiResponse.<String>builder()
                .success(isRegistered)
                .message(isRegistered ? "Registration successful" : "User already exists")
                .result(isRegistered ? "OK" : "Registration failed")
                .build();
    }

}
