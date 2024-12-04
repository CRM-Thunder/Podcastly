package com.zamecki.Podcastly.FileUploadEntity.Exceptions;

public class PostNotSavedException extends RuntimeException {
    public PostNotSavedException(String message) {
        super(message);
    }
}
