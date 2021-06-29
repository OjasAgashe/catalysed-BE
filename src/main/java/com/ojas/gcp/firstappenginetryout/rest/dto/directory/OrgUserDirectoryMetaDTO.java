package com.ojas.gcp.firstappenginetryout.rest.dto.directory;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

import java.util.List;

public class OrgUserDirectoryMetaDTO {
    private Long id;
    private UserType type;
    private String name;
    private String email;
    private List<String> topPrograms;

    public OrgUserDirectoryMetaDTO() {

    }

    public OrgUserDirectoryMetaDTO(Long id, String name, String email, List<String> topPrograms, UserType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.topPrograms = topPrograms;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTopPrograms() {
        return topPrograms;
    }

    public void setTopPrograms(List<String> topPrograms) {
        this.topPrograms = topPrograms;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
