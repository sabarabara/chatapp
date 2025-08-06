package com.javaapi.app.user.framework.session;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;



@Component

public class SessionStore {


    public String getUserid(Session session) {
        return session.getAttribute("userId");

    }
    
    public String getUsername(Session session) {
        return session.getAttribute("username");

    }

    public String getEmail(Session session) {
        return session.getAttribute("email");

    }

    public String setUserid(Session session, String userId) {
        session.setAttribute("userId", userId);
        return userId;
    }
    public String setUsername(Session session, String username) {
        session.setAttribute("username", username);
        return username;
    }
    public String setEmail(Session session, String email) {
        session.setAttribute("email", email);
        return email;
    }
}