package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
public class ConnectedOrgController {
    private final OrganizationService orgService;

    public ConnectedOrgController(OrganizationService orgService) {
        this.orgService = orgService;
    }


    @GetMapping(value = "user/{userId}/connected/organizations")
    public ResponseEntity<Object> getOrgProfile(@PathVariable Long userId,
                                                @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        validateUserAccessingSelfResource(authentication, userId);
        //check user has completed profile building and has org connected
        return ResponseEntity.ok(orgService.getConnectedOrgs(userId));
    }

    @GetMapping(value = "user/{userId}/connected/organizations/{orgId}")
    public ResponseEntity<Object> getConnectedOrgDetailsForUser(@PathVariable Long userId, @PathVariable Long orgId,
                                                @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        validateUserAccessingSelfResource(authentication, userId);
        //check user has completed profile building and has org connected
        return ResponseEntity.ok(orgService.getConnectedOrgDetails(userId, orgId));
    }

    private void validateUserAccessingSelfResource(Authentication authentication, Long userId) throws ValidationException {
        SessionUser user = (SessionUser) authentication.getPrincipal();
        if (!user.getId().equals(userId)) {
            throw new ValidationException("Logged-In user cannot access the resource");
        }
    }
}
