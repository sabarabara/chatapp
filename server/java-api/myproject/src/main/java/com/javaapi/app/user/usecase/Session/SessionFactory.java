package com.javaapi.app.user.usecase.Session;

import org.springframework.stereotype.Service;

import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.framework.session.SessionStore;


@Service
public class SessionFactory{

    private final SessionStore sessionStore;
    public SessionFactory(SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    public void createUserSession(SessionDTO sessionDTO,String session) {

        sessionStore.saveSession(session, sessionDTO.getUserId(), sessionDTO.getUsername(), sessionDTO.getEmail());
    }
}