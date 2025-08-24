package com.javaapi.app.user.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.dto.UserDTO;
import com.javaapi.app.user.usecase.User.UserUsecase;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUsecase userUsecase;

    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        return userUsecase.registerUser(userDTO);
    };

    @PostMapping("/login")
    public String loginUser(@RequestBody Userid userid,HttpSession session) {
        return userUsecase.loginUser(userid, session);
}
}