package ios.istad.mbanking.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtFailureEvents {
    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent badCredentials){
        if(badCredentials.getAuthentication() instanceof BearerTokenAuthenticationToken){
            System.out.println(badCredentials.getException().getMessage());
        }
    }
}
