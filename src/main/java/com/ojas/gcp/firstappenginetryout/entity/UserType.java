package com.ojas.gcp.firstappenginetryout.entity;

public enum UserType {
    STUDENT("Student"),
    MENTOR("Mentor"),
    ORGANIZATION_ADMIN("Organization Admin");
    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
