package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramsController {

    @Autowired
    private ProgramService programService;

    @PostMapping(value = "program")
    public ResponseEntity<Object> createProgram(@RequestBody ProgramDTO programDTO) throws Exception {
        //add validations
        ProgramDTO createdProgramDTO = programService.createProgram(programDTO);
        return ResponseEntity.ok(createdProgramDTO);
    }

    @GetMapping(value = "program/{id}")
    public ResponseEntity<Object> getProgram(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(programService.getProgram(id));
    }

    @GetMapping(value = "organization/programs")
    public ResponseEntity<Object> getPrograms() throws Exception {
        //get users logged in org id
        Long orgId = 1l;
        return ResponseEntity.ok(programService.getProgramsForOrg(orgId));
    }
}
