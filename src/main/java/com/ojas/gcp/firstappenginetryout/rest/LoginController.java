package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.AppUserDetailsService;
import com.ojas.gcp.firstappenginetryout.auth.AuthenticationRequest;
import com.ojas.gcp.firstappenginetryout.auth.AuthenticationResponse;
import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.AuthenticationUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.security.JwtUtils;
import com.ojas.gcp.firstappenginetryout.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/authenticate")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailsService userDetailsService;
    @Autowired
    private OrganizationService organizationService;
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

    private AuthenticationUserDTO generateAuthUserDTO(SessionUser sessionUser) throws ValidationException {
        AuthenticationUserDTO authUser =  new AuthenticationUserDTO(
                sessionUser.getId(),
                sessionUser.getEmailId(),
                sessionUser.getUsername(),
                null,
                null,
                sessionUser.isEnabled(),
                sessionUser.getUserType(),
                sessionUser.isAccountVerified(),
                sessionUser.isProfileCreated()
        );

        if (authUser.getUserType() == UserType.ORGANIZATION_USER) {
            OrgMetaDTO org = organizationService.getOrgMetaForOrgUser(sessionUser);
            authUser.setOrgId(org.getId());
            authUser.setOrgName(org.getName());
        } else if (!authUser.isProfileCreated()){    //non-org users need org details only for first time profile builder
            List<OrgMetaDTO> orgs = organizationService.getConnectedOrgs(sessionUser.getId());
            if (CollectionUtils.isEmpty(orgs)) {
                throw new ValidationException("User not connected to any organization");
            }
            authUser.setOrgName(orgs.get(0).getName());
            authUser.setOrgId(orgs.get(0).getId());
        }
        return authUser;
    }
}
