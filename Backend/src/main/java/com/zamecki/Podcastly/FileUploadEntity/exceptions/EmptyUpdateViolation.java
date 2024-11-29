package com.zamecki.Podcastly.FileUploadEntity.exceptions;

public class EmptyUpdateViolation extends RuntimeException {
    public EmptyUpdateViolation(String message) {
        super(message);
    }
}
