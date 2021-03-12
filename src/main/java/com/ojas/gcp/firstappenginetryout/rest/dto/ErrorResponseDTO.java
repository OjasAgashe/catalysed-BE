package com.ojas.gcp.firstappenginetryout.rest.dto;

public class ErrorResponseDTO {
    private String timestamp;
    private String message;

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
