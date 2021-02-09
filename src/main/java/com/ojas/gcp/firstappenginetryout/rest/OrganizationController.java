package com.ojas.gcp.firstappenginetryout.rest;


import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;
import com.ojas.gcp.firstappenginetryout.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organization")
public class OrganizationController {
    private OrganizationService orgService;

    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.orgService = orgService;
    }

    @PostMapping(value = "/details")
    public ResponseEntity<Object> setOrgDetails(@RequestBody OrganizationDetailsDTO detailsDTO,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        OrganizationDetailsDTO orgDetails = orgService.setOrgDetails(user, detailsDTO);
        return ResponseEntity.ok(orgDetails);
    }
}
