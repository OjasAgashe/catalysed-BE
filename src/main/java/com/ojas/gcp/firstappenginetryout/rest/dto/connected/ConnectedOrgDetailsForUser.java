package com.ojas.gcp.firstappenginetryout.rest.dto.connected;

import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import java.util.List;

public class ConnectedOrgDetailsForUser {
    private OrgDetailsProfileDTO orgDetails;
    private List<ProgramOrgMetaDTO> programs;

    public ConnectedOrgDetailsForUser() {

    }

    public ConnectedOrgDetailsForUser(OrgDetailsProfileDTO orgDetails, List<ProgramOrgMetaDTO> programs) {
        this.orgDetails = orgDetails;
        this.programs = programs;
    }

    public OrgDetailsProfileDTO getOrgDetails() {
        return orgDetails;
    }

    public void setOrgDetails(OrgDetailsProfileDTO orgDetails) {
        this.orgDetails = orgDetails;
    }

    public List<ProgramOrgMetaDTO> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramOrgMetaDTO> programs) {
        this.programs = programs;
    }
}
