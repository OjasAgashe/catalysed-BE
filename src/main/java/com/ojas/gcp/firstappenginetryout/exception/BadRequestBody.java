package com.ojas.gcp.firstappenginetryout.exception;

public class BadRequestBody extends RuntimeException{
    public BadRequestBody(String message) {
        super(message);
    }
    public BadRequestBody(String message, Throwable cause) {
        super(message, cause);
    }
}
