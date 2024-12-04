package com.zamecki.Podcastly.FileUploadEntity.Exceptions;

public class FileTypeViolationException extends RuntimeException {
    public FileTypeViolationException(String message) {
        super(message);
    }
}
