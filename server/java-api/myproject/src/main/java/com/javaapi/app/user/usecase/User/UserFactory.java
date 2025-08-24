package com.javaapi.app.user.usecase.User;

import org.springframework.stereotype.Service;

import com.javaapi.app.user.core.domain.model.vo.Email;
import com.javaapi.app.user.core.domain.model.vo.Username;
import com.javaapi.app.user.core.dto.UserDTO;
import com.javaapi.app.user.core.entity.UserEntity;


@Service
public class UserFactory{

    public UserEntity createUser (UserDTO userDTO) {

        Username username = new Username(userDTO.getUsername());
        Email email = new Email(userDTO.getEmail());

        String validUsername = username.getUsername();
        String validEmail = email.getEmail();

        UserEntity userEntity = new UserEntity(validUsername, validEmail);
        System.out.println(userEntity);
        return userEntity;
    }
}