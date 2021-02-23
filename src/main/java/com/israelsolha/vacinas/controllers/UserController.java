package com.israelsolha.vacinas.controllers;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.requests.UserRequest;
import com.israelsolha.vacinas.models.response.UserResponse;
import com.israelsolha.vacinas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<UserResponse> insert(@Valid @RequestBody UserRequest userRequest) {
        User user = userRequest.toModel();
        user = userService.insert(user);
        UserResponse userResponse = user.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

}
