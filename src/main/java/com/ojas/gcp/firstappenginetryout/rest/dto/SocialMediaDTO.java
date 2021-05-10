package com.ojas.gcp.firstappenginetryout.rest.dto;

public class SocialMediaDTO {
    private String code;
    private String link;

    public SocialMediaDTO() {

    }

    public SocialMediaDTO(String code, String link) {
        this.code = code;
        this.link = link;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
