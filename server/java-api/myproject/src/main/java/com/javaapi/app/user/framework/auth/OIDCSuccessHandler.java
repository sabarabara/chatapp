package com.javaapi.app.user.framework.auth;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

        // OidcUser を取得
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        // ユーザーIDをUUIDとして取得
        String userId = UUID.fromString(oidcUser.getSubject()).toString();
        String username = oidcUser.getPreferredUsername();
        String email = oidcUser.getEmail();


        UserEntity userEntity = new UserEntity(username, email);
        userEntity.setUserid(userId);
        userRepo.save(userEntity);

        // セッションにユーザー情報を保存
        sessionStore.setUserid(session, userId);
        sessionStore.setUsername(session, username);
        sessionStore.setEmail(session, email);


        //idpのsessionを廃棄する
        idpLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        response.sendRedirect("/actuator/health");
    }
}
