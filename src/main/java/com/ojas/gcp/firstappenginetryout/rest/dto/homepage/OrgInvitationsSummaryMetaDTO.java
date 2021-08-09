package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgInvitationsSummaryMetaDTO {
    private int total;
    private int accepted;
    private int pending;

    public OrgInvitationsSummaryMetaDTO() {

    }

    public OrgInvitationsSummaryMetaDTO(int total, int accepted, int pending) {
        this.total = total;
        this.accepted = accepted;
        this.pending = pending;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }
}
