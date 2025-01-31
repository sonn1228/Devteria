package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.in.UserCreationRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createReq(UserCreationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
