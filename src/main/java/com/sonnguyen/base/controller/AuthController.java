package com.sonnguyen.base.controller;

import com.sonnguyen.base.dto.response.IntrospectResponse;
import com.sonnguyen.base.dto.request.ApiResponse;
import com.sonnguyen.base.dto.request.AuthRequest;
import com.sonnguyen.base.dto.request.IntrospectRequest;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.utils.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .success(true)
                        .message("login success")
                        .data(authService.login(authRequest.getUsername(), authRequest.getPassword()))
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest authRequest) {
        authService.register(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .success(true)
                        .message("register success")
                        .build()
        );
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        boolean isValid = authService.introspect(request.getToken());
        String message = isValid ? "Token is valid" : "Token is invalid";
        return ApiResponse.<IntrospectResponse>builder()
                .success(isValid)
                .message(message)
                .data(IntrospectResponse.builder()
                        .valid(isValid)
                        .build())
                .build();
    }

}
