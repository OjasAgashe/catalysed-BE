package com.ojas.gcp.firstappenginetryout.entity.enums;

public enum UserType {
    STUDENT("Student"),
    MENTOR("Mentor"),
    ORGANIZATION_USER("Organization User");
    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
