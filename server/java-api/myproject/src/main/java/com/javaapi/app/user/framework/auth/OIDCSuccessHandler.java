package com.javaapi.app.user.framework.auth;

import java.io.IOException;
import java.util.UUID;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.entity.UserEntity;
import com.javaapi.app.user.framework.session.SessionStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class OIDCSuccessHandler implements AuthenticationSuccessHandler {

    private final SessionStore sessionStore;
    private final IDPLogoutSuccessHandler idpLogoutSuccessHandler;
    private final IUserRepo userRepo;

    public OIDCSuccessHandler(SessionStore sessionStore, IDPLogoutSuccessHandler idpLogoutSuccessHandler, IUserRepo userRepo) {
        this.sessionStore = sessionStore;
        this.idpLogoutSuccessHandler = idpLogoutSuccessHandler;
        this.userRepo = userRepo;
    }

    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        System.out.println("🐞sSession ID: " + session.getId());
        // OidcUser を取得
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();


        String userId = oidcUser.getSubject();
        String username = "kana";
        String email = "kana@example.com";

        UserEntity userEntity = new UserEntity(username, email);
        userEntity.setUserid(userId);
        userRepo.save(userEntity);


        sessionStore.setUserid(session.getId(), userId);
        sessionStore.setUsername(session.getId(), username);
        sessionStore.setEmail(session.getId(), email);


        //idpのsessionを廃棄する
        //idpLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        RestTemplate restTemplate = new RestTemplate();
        String response2 = restTemplate.getForObject(
        "http://localhost:5000/callback/?sessionid=" + session.getId(), String.class);
        System.out.println(response2);

        response.sendRedirect("/");
    }
}
