package com.ojas.gcp.firstappenginetryout.entity.internshipTest;

public enum Phase {
    APPLICATION_OPEN("Application Open"),
    APPLICATION_REVIEW("Application Review"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");
    private String value;

    Phase(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
