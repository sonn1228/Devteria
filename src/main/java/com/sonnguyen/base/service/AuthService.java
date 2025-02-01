package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.out.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
    void register(String username, String password);
    boolean introspect(String token);
}
