package com.ojas.gcp.firstappenginetryout.rest;


import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;
import com.ojas.gcp.firstappenginetryout.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> createStudent(@RequestBody OrganizationDetailsDTO detailsDTO) {
        //get user from userPrinciple
        OrganizationDetailsDTO orgDetails = orgService.setOrgDetails(detailsDTO);
        return ResponseEntity.ok(orgDetails);
    }
}
