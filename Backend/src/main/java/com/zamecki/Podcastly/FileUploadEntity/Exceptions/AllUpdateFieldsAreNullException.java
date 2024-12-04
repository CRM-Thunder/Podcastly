package com.zamecki.Podcastly.FileUploadEntity.Exceptions;

public class AllUpdateFieldsAreNullException extends RuntimeException {
    public AllUpdateFieldsAreNullException(String message) {
        super(message);
    }
}
