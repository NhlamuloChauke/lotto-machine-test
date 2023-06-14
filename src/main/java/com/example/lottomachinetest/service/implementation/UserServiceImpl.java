package com.example.lottomachinetest.service.implementation;

import com.example.lottomachinetest.entity.User;
import com.example.lottomachinetest.repository.UserRepository;
import com.example.lottomachinetest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public User createUser(User userRequest) {

        // Create a new user instance
        User user = new User();
        user.setUsername(user.getUsername());
        user.setBalance(BigInteger.ZERO); // Set the initial balance

        // Save the user using the JPA repository
        userRepository.save(user);
        return user;
    }
}
