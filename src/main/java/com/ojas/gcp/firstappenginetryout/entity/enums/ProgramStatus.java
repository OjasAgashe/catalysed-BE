package com.ojas.gcp.firstappenginetryout.entity.enums;

public enum ProgramStatus {
    SAVED_TO_DRAFT("SAVED_TO_DRAFT"),
    PUBLISHED("PUBLISHED");

    private final String value;
    ProgramStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
