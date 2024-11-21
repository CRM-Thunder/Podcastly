package com.zamecki.Podcastly.FileUploadEntity.exceptions;

import java.io.IOException;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
