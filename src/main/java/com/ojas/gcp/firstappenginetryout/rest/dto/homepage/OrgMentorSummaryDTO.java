package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgMentorSummaryDTO {
    private int total;
    private int newThisMonth;

    public OrgMentorSummaryDTO() {

    }

    public OrgMentorSummaryDTO(int total, int newThisMonth) {
        this.total = total;
        this.newThisMonth = newThisMonth;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNewThisMonth() {
        return newThisMonth;
    }

    public void setNewThisMonth(int newThisMonth) {
        this.newThisMonth = newThisMonth;
    }
}
