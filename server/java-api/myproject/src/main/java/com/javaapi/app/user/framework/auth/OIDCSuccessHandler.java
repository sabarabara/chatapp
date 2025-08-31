package com.javaapi.app.user.framework.auth;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.javaapi.app.user.framework.session.SessionStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class OIDCSuccessHandler implements AuthenticationSuccessHandler {

    private final SessionStore sessionStore;
    private final IDPLogoutSuccessHandler idpLogoutSuccessHandler;

    public OIDCSuccessHandler(SessionStore sessionStore, IDPLogoutSuccessHandler idpLogoutSuccessHandler) {
        this.sessionStore = sessionStore;
        this.idpLogoutSuccessHandler = idpLogoutSuccessHandler;
    }

    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // OidcUser を取得
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        // ユーザーIDをUUIDとして取得
        UUID userId = UUID.fromString(oidcUser.getSubject());

        // セッションにユーザー情報を保存
        sessionStore.setUserid(session, userId);
        sessionStore.setUsername(session, oidcUser.getPreferredUsername());
        sessionStore.setEmail(session, oidcUser.getEmail());

        //idpのsessionを廃棄する
        idpLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        // 認証成功後はデフォルトのリダイレクト先へ
        response.sendRedirect("/");  
    }
}
