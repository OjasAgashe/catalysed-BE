package com.ojas.gcp.firstappenginetryout.entity.enums;

public enum OrgSubscriptionType {
    BASIC("basic"),
    PRO("pro");
    private String value;

    OrgSubscriptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
