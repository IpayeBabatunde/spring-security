package com.ipaye.springsecurity.Service;

import com.ipaye.springsecurity.Model.Users;
import com.ipaye.springsecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    // Bcrypt library (password security)
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){

       // Bcrypt library (password security)
        user.setPassword(encoder.encode(user.getPassword()));

      return  repo.save(user);

    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "fail";
    }
}
