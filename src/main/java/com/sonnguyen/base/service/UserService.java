package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.request.UserCreationRequest;
import com.sonnguyen.base.dto.request.UserUpdateRequest;
import com.sonnguyen.base.enums.Role;
import com.sonnguyen.base.exception.CommonException;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CommonException("Username already exists", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setUsername(request.getUsername());

        user.getRoles().add(Role.USER.name());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String userId, UserUpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException("User not found", HttpStatus.NOT_FOUND));

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CommonException("User not found", HttpStatus.NOT_FOUND));
    }

    public void deleteById(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new CommonException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}
