package com.ipaye.springsecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String Hello(HttpServletRequest request){
        return "Welcome to Insta " + request.getSession().getId();
    }
}
