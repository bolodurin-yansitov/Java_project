package com.example.prototipe.config;

import com.example.prototipe.common.Password;
import com.example.prototipe.daos.UsersDao;
import com.example.prototipe.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(
            CustomAuthenticationProvider.class);

    private UsersDao usersDao;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info(email);
        logger.info(password);

        //todo Upgrade authentication
        Users user = usersDao.findByEmail(email);
        if(user == null)    return null;

        if(Password.isExpectedPassword(password.toCharArray(),
                user.getPassword_salt(), user.getPassword_hash()))
            return new UsernamePasswordAuthenticationToken(email,
                    password, new ArrayList<>());
        else    return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
