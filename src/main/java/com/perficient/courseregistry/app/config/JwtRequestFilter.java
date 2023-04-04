package com.perficient.courseregistry.app.config;
import com.perficient.courseregistry.app.exception.custom.RoleException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.perficient.courseregistry.app.utils.adapters.Constants.CLAIMS_ROLES_KEY;
import static com.perficient.courseregistry.app.utils.adapters.Constants.COOKIE_NAME;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Value( "${app.secret}" )
    String secret;

    public JwtRequestFilter() {}

    private Optional<String> getToken(HttpServletRequest request){
        String authHeader = request.getHeader( HttpHeaders.AUTHORIZATION );
        Optional<Cookie> optionalCookie = request.getCookies() != null ? Arrays.stream( request.getCookies() )
                                                                               .filter(cookie -> Objects.equals( cookie.getName(), COOKIE_NAME ) )
                                                                               .findFirst() : Optional.empty();
        Optional<String> headerJwt = Optional.empty();
        if ( authHeader != null && authHeader.startsWith( "Bearer " ) ) headerJwt = Optional.of(authHeader.substring( 7 ));
        return optionalCookie.map(cookie -> Optional.of(cookie.getValue())).orElse(headerJwt);
    }

    private HttpServletRequest saveUserAuthInfo(String token, HttpServletRequest request){
        Jws<Claims> claims = Jwts.parser().setSigningKey( secret ).parseClaimsJws( token);
        Claims claimsBody = claims.getBody();
        String subject = claimsBody.getSubject();
        String role  = claims.getBody().get( CLAIMS_ROLES_KEY , String.class);
        if (role == null) throw new RoleException();
        else SecurityContextHolder.getContext().setAuthentication( new TokenAuthentication( token, subject, role));
        request.setAttribute( "claims", claimsBody );
        request.setAttribute( "jwtUserId", subject );
        request.setAttribute("jwtUserRoles", role);
        return request;
    }
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
        try {
            Optional<String> token = getToken(request);
            token.ifPresent(t -> saveUserAuthInfo(t, request));
            filterChain.doFilter( request, response );
            } catch (RoleException exception){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            } catch ( MalformedJwtException e ) {
                response.sendError( HttpStatus.BAD_REQUEST.value(), "Missing or wrong token" );
            } catch ( ExpiredJwtException e ) {
                response.sendError( HttpStatus.UNAUTHORIZED.value(), "Token expired or malformed" );
            }
    }

}