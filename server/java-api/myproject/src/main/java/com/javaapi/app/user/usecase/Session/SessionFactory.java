package com.javaapi.app.user.usecase.Session;

import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.framework.session.SessionStore;

import jakarta.servlet.http.HttpSession;


public class SessionFactory{

    private final SessionStore sessionStore;
    public SessionFactory(SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    public void createUserSession(SessionDTO sessionDTO,HttpSession session) {

        sessionStore.setUserid(session, sessionDTO.getUserId());
        sessionStore.setUsername(session, sessionDTO.getUsername());
        sessionStore.setEmail(session, sessionDTO.getEmail());
    }
}