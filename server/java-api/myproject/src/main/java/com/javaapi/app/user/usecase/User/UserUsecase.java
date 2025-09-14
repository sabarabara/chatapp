package com.javaapi.app.user.usecase.User;

import org.springframework.stereotype.Service;

import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.core.dto.UserDTO;
import com.javaapi.app.user.core.entity.UserEntity;
import com.javaapi.app.user.usecase.Session.SessionUsecase;


@Service
public class UserUsecase {

    private final UserFactory userFactory;
    private final IUserRepo userRepo;
    private final SessionUsecase sessionUsecase;

    public UserUsecase(UserFactory userFactory, IUserRepo userRepo, SessionUsecase sessionUsecase) {
        this.userFactory = userFactory;
        this.userRepo = userRepo;
        this.sessionUsecase = sessionUsecase;
    }

    public String registerUser(UserDTO userDTO) {
        UserEntity newUser = userFactory.createUser(userDTO);
        System.out.println("👹👹Registering user: 👹👹👹👹" + newUser);
        userRepo.save(newUser);

        return "OK";
    }


    public String loginUser(Userid userid,String session) {
        UserEntity userEntity = userRepo.findByUserid(userid.getUserid());

        SessionDTO sessionDTO = new SessionDTO(
            userEntity.getUserid(),
            userEntity.getUsername(),
            userEntity.getEmail()
        );

        return sessionUsecase.createUserSession(sessionDTO,session);
    }
}