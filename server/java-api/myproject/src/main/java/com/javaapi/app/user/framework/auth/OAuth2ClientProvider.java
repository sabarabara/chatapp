package com.javaapi.app.user.framework.auth;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

@Component
public class OAuth2ClientProvider {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public OAuth2ClientProvider(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public ClientRegistration getCognitoClient() {
        return clientRegistrationRepository.findByRegistrationId("cognito");
    }
}