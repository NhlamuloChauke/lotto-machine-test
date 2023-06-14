package com.example.lottomachinetest.controller;

import com.example.lottomachinetest.entity.User;
import com.example.lottomachinetest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotto/machine")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<User> createUser(@RequestBody User request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
