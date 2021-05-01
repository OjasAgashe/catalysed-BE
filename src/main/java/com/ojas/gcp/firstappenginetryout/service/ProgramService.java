package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;

import java.util.List;

public interface ProgramService {
    public ProgramDTO createProgram(ProgramDTO programDTO) throws Exception;

    public ProgramDTO getProgram(Long id) throws Exception;

    public List<ProgramOrgMetaDTO> getProgramsForOrg(Long orgId);
}
