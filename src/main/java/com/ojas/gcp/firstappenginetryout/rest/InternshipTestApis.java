package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.service.InternshipTestService;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class InternshipTestApis {
    private InternshipTestService programService;

    @Autowired
    public InternshipTestApis(InternshipTestService programService) {
        this.programService = programService;
    }

    @GetMapping(value = "programs")
    public ResponseEntity<Object> getProgramsMeta() {
        return ResponseEntity.ok(programService.getPrograms());
    }

    @GetMapping(value = "programs/{programId}")
    public ResponseEntity<Object> getProgramDetails(@PathVariable Long programId) {
        if(programId <= 0 || programId > 7) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Program with specified Id : %s not found", programId));
        }


        return ResponseEntity.ok(programService.getProgramDetails(programId));
    }


    @GetMapping(value = "mentors")
    public ResponseEntity<Object> getMentorsMeta() {
        return ResponseEntity.ok(programService.getMentors());
    }

    @GetMapping(value = "mentors/{mentorId}")
    public ResponseEntity<Object> getMentorDetails(@PathVariable Long mentorId) {
        if(mentorId <= 0 || mentorId > 5) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Mentor with specified Id : %s not found", mentorId));
        }
        return ResponseEntity.ok(programService.getMentorDetails(mentorId));
    }
}
