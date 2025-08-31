package com.javaapi.app.user.framework.auth;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;

@Component
public class IDPLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ClientRegistrationRepository clients;

    @Autowired
    public IDPLogoutSuccessHandler(ClientRegistrationRepository clients) {
        this.clients = clients;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ClientRegistration cognito = clients.findByRegistrationId("cognito");

        
        String logoutUrl = String.format(
            "https://%s/logout?client_id=%s",
            cognito.getProviderDetails().getIssuerUri(),
            cognito.getClientId()
        );

        response.sendRedirect(logoutUrl);
    }
}
