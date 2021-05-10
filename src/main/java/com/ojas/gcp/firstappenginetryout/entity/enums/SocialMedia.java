package com.ojas.gcp.firstappenginetryout.entity.enums;

public enum SocialMedia {
    LINKED_IN("LINKED_IN", "https://www.linkedin.com/"),
    FACEBOOK("FACEBOOK", "https://www.facebook.com/"),
    TWITTER("TWITTER", "https://twitter.com/");
    private String value;
    private String baseURL;

    SocialMedia(String value, String baseURL) {
        this.value = value;
        this.baseURL = baseURL;
    }

    public String getValue() {
        return value;
    }

    public String getBaseURL() {
        return baseURL;
    }
}
