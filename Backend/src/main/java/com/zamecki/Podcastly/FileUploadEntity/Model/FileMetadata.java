package com.zamecki.Podcastly.FileUploadEntity.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileMetadata {
    private String filename;
    private long size;
    private String contentType;
}
