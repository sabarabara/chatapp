package com.javaapi.app.user.framework.session;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;



@Component

public class SessionStore {


    public Object getUserid(HttpSession session) {
        return session.getAttribute("userId");

    }
    
    public Object getUsername(HttpSession session) {
        return session.getAttribute("username");

    }

    public Object getEmail(HttpSession session) {
        return session.getAttribute("email");

    }

    public String setUserid(HttpSession session, String userId) {
        session.setAttribute("userId", userId);
        return userId;
    }
    public String setUsername(HttpSession session, String username) {
        session.setAttribute("username", username);
        return username;
    }
    public String setEmail(HttpSession session, String email) {
        session.setAttribute("email", email);
        return email;
    }
}