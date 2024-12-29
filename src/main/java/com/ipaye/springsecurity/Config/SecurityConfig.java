package com.ipaye.springsecurity.Config;

import org.apache.coyote.Request;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

       return httpSecurity.csrf(Customizer -> Customizer.disable())
                    .authorizeHttpRequests(Request -> Request.anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 =User
                .withDefaultPasswordEncoder()
                .username("ovie")
                .password("1234")
                .roles("USER")
                .build();
        UserDetails user2 =User
                .withDefaultPasswordEncoder()
                .username("benny")
                .password("1234")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1, user2);

    }

}
