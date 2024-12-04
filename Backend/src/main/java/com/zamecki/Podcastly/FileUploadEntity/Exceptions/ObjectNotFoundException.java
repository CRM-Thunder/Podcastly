package com.zamecki.Podcastly.FileUploadEntity.Exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
