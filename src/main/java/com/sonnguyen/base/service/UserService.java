package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.request.UserCreationRequest;
import com.sonnguyen.base.dto.request.UserUpdateRequest;
import com.sonnguyen.base.exception.AppException;
import com.sonnguyen.base.exception.ErrorCode;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setDob(request.getDob());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));
    }

    public void deleteById(String userId){
        userRepository.deleteById(userId);
    }
}
