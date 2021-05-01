package com.ojas.gcp.firstappenginetryout.entity.internshipTest;

public class ProgramMeta {
    private Long id;
    private String name;
    private String category;
    private String shortDescription;
    private Phase phase;
    private String startDate;
    private String duration;

    public ProgramMeta() {
    }

    public ProgramMeta(Long id, String name, String category, String shortDescription, Phase phase, String startDate,
                       String duration) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.shortDescription = shortDescription;
        this.phase = phase;
        this.startDate = startDate;
        this.duration = duration;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
