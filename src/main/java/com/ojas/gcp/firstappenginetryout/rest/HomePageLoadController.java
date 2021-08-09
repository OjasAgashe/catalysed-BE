package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgApplicationsSummaryMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgInvitationsSummaryMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgMentorSummaryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgStudentSummaryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgUserHomePageDTO;
import com.ojas.gcp.firstappenginetryout.service.HomePageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageLoadController {

    private final HomePageService homePageService;

    public HomePageLoadController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping(value = "/organizations/{orgId}/homepage")
    public ResponseEntity<Object> getOrgHomePage(@PathVariable Long orgId,
                                                 @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        OrgUserHomePageDTO orgUserHome = homePageService.getOrgUserHomePageDetails(user, orgId);
        return ResponseEntity.ok(orgUserHome);
    }

    @GetMapping(value = "/organizations/{orgId}/invitations/summary")
    public ResponseEntity<Object> getOrgInvitationSummary(@PathVariable Long orgId,
                                                 @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        OrgUserHomePageDTO orgUserHome = homePageService.getOrgUserHomePageDetails(user, orgId);
        return ResponseEntity.ok(orgUserHome);
    }

    @GetMapping(value = "/organizations/{orgId}/applications/summary")
    public ResponseEntity<Object> getOrgApplicationSummary(@PathVariable Long orgId,
                                                 @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        OrgUserHomePageDTO orgUserHome = homePageService.getOrgUserHomePageDetails(user, orgId);
        return ResponseEntity.ok(orgUserHome);
    }

    @GetMapping(value = "/students/{studentId}/homepage")
    public ResponseEntity<Object> getStudentHomePage(@PathVariable Long studentId,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
//        SessionUser user = (SessionUser)authentication.getPrincipal();
//        OrganizationDetailsDTO orgDetails = orgService.setOrgDetails(user, detailsDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/mentors/{mentorId}/homepage")
    public ResponseEntity<Object> getMentorHomePage(@PathVariable Long mentorId,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
//        SessionUser user = (SessionUser)authentication.getPrincipal();
//        OrganizationDetailsDTO orgDetails = orgService.setOrgDetails(user, detailsDTO);
        return ResponseEntity.ok("ok");
    }



}
