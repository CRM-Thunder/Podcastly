package com.zamecki.Podcastly.FileUploadEntity.exceptions;

public class ContentTypeViolation extends RuntimeException {
    public ContentTypeViolation(String message) {
        super(message);
    }
}
