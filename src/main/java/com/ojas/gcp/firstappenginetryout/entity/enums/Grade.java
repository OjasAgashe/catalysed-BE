package com.ojas.gcp.firstappenginetryout.entity.enums;

public enum Grade {
    PRE("Pre School"),
    PRIMARY("Primary School"),
    SECONDARY("Secondary School");
    private String value;

     Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
