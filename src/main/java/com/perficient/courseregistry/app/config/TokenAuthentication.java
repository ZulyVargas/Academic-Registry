package com.perficient.courseregistry.app.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenAuthentication extends AbstractAuthenticationToken {
    String token;
    String subject;
    String role;

    public TokenAuthentication( String token, String subject, String role ) {
        super( null );
        this.token = token;
        this.subject = subject;
        this.role = role;
    }

    @Override
    public boolean isAuthenticated() {
        return !token.isEmpty() && !subject.isEmpty() && !role.isEmpty();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority( "ROLE_" + role ) );
    }


}
