package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.AppUserDetailsService;
import com.ojas.gcp.firstappenginetryout.auth.AuthenticationRequest;
import com.ojas.gcp.firstappenginetryout.auth.AuthenticationResponse;
import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.AuthenticationUserDTO;
import com.ojas.gcp.firstappenginetryout.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtTokenUtils;

    @PostMapping
    public ResponseEntity<?> authenticateAppUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
////            throw new Exception("Incorrect email or password");
//        }
        System.out.println("Creating session object");

        SessionUser userDetails = (SessionUser)userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(generateAuthUserDTO(userDetails), jwt));
    }

    private AuthenticationUserDTO generateAuthUserDTO(SessionUser sessionUser) {
        return new AuthenticationUserDTO(
                sessionUser.getId(),
                sessionUser.getEmailId(),
                sessionUser.getUsername(),
                sessionUser.isEnabled(),
                sessionUser.getUserType(),
                sessionUser.isAccountVerified(),
                sessionUser.isProfileCreated());
    }
}
