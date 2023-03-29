package com.perficient.courseregistry.app.controller.auth;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.dto.auth.LoginDTO;
import com.perficient.courseregistry.app.dto.auth.TokenDTO;
import com.perficient.courseregistry.app.exception.custom.InvalidCredentialsException;
import com.perficient.courseregistry.app.services.impl.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.perficient.courseregistry.app.utils.adapters.Constants.CLAIMS_ROLES_KEY;
import static com.perficient.courseregistry.app.utils.adapters.Constants.TOKEN_DURATION_MINUTES;

@RestController
@RequestMapping( "v1/auth" )
public class AuthController {

    @Value( "${app.secret}" )
    String secret;

    private final UserService userService;

    public AuthController( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping
    public TokenDTO login(@RequestBody LoginDTO loginDto ) {
        UserDTO userDTO = userService.findByEmail( loginDto.getEmail() );
        if ( loginDto.getPassword().equals(userDTO.getPassword() ) ) {
            return generateTokenDto( userDTO );
        }
        else {
            throw new InvalidCredentialsException();
        }
    }

    private String generateToken( UserDTO user, Date expirationDate ) {
        return Jwts.builder()
                .setSubject(user.getUserId().toString())
                .claim( CLAIMS_ROLES_KEY, user.getRole() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDTO generateTokenDto( UserDTO user ) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDTO( token, expirationDate.getTime() );
    }
}