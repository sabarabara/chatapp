package com.javaapi.app.user.usecase.Session;

import org.springframework.stereotype.Service;

import com.javaapi.app.user.core.domain.model.vo.Email;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.model.vo.Username;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.framework.session.SessionStore;

import jakarta.servlet.http.HttpSession;


@Service
public class SessionUsecase{

    private final SessionFactory sessionFactory;
    private final SessionStore sessionStore;

    public SessionUsecase(SessionFactory sessionFactory,SessionStore sessionStore) {
        this.sessionFactory = sessionFactory;
        this.sessionStore = sessionStore;
    }


    public String createUserSession(SessionDTO sessionDTO,HttpSession session){
        sessionFactory.createUserSession(sessionDTO,session);

        return "OK";
    }

    public SessionDTO getUserSession(HttpSession session){

        String userid = sessionStore.getUserid(session).toString();
        String username = sessionStore.getUsername(session).toString();
        String email = sessionStore.getEmail(session).toString();

        Userid valUserid = new Userid(userid);
        Username valUsername = new Username(username);
        Email valEmail = new Email(email);

        SessionDTO sessionDTO = new SessionDTO(valUserid.getUserid(), valUsername.getUsername(), valEmail.getEmail());
        return sessionDTO;
    }
}