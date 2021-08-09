package com.ojas.gcp.firstappenginetryout.rest.dto.invitations;

import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;

public class UserViewProgramInvitationDTO {
    private ProgramInvitationDTO inviteDetails;
    private ProgramDTO programDetails;

    public UserViewProgramInvitationDTO() {

    }

    public UserViewProgramInvitationDTO(ProgramInvitationDTO inviteDetails, ProgramDTO programDetails) {
        this.inviteDetails = inviteDetails;
        this.programDetails = programDetails;
    }

    public ProgramInvitationDTO getInviteDetails() {
        return inviteDetails;
    }

    public void setInviteDetails(ProgramInvitationDTO inviteDetails) {
        this.inviteDetails = inviteDetails;
    }

    public ProgramDTO getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(ProgramDTO programDetails) {
        this.programDetails = programDetails;
    }
}
