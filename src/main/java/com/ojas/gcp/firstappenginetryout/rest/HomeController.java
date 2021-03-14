package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Value("${message}")
    String message;
    @GetMapping(value = "appEngine/landing")
    public ResponseEntity<String> displayHelloMessage() {
        return ResponseEntity.ok(message);
    }

//    @GetMapping("/")
//    public String helloAll() {
//        return ("<h1>Welcome All</h1>");
//    }

    @GetMapping("/user")
    public String helloUser(@AuthenticationPrincipal Authentication authentication) {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return (String.format("<h1>Welcome %s</h1>", user.getUsername()));
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return ("<h1>Welcome Admin</h1>");
    }
}

