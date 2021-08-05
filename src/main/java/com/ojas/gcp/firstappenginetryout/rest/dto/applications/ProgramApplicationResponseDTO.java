package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

public class ProgramApplicationResponseDTO {
    private int responseNumber;
    private String question;
    private String answer;

    public ProgramApplicationResponseDTO() {

    }

    public ProgramApplicationResponseDTO(int responseNumber, String question, String answer) {
        this.responseNumber = responseNumber;
        this.question = question;
        this.answer = answer;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(int responseNumber) {
        this.responseNumber = responseNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
