package com.ojas.gcp.firstappenginetryout.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    public String helloUser() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return ("<h1>Welcome Admin</h1>");
    }
}

