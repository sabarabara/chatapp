package com.javaapi.app.user.usecase.Session;

import org.springframework.stereotype.Service;

import com.javaapi.app.user.core.domain.model.vo.Email;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.model.vo.Username;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.framework.session.SessionStore;


@Service
public class SessionUsecase{

    private final SessionFactory sessionFactory;
    private final SessionStore sessionStore;

    public SessionUsecase(SessionFactory sessionFactory,SessionStore sessionStore) {
        this.sessionFactory = sessionFactory;
        this.sessionStore = sessionStore;
    }


    public String createUserSession(SessionDTO sessionDTO,String session){
        sessionFactory.createUserSession(sessionDTO,session);

        return "OK";
    }

    public SessionDTO getUserSession(String session){

        SessionDTO userInfo = sessionStore.getUserInfo(session);

        System.out.println("🐞 Retrieved UserInfo: userId=" + userInfo.getUserId());
        Userid valUserid = new Userid(userInfo.getUserId());
        Username valUsername = new Username(userInfo.getUsername());
        Email valEmail = new Email(userInfo.getEmail());

        SessionDTO sessionDTO = new SessionDTO(valUserid.getUserid(), valUsername.getUsername(), valEmail.getEmail());
        return sessionDTO;
    }
}