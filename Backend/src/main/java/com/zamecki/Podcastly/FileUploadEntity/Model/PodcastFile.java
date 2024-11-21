package com.zamecki.Podcastly.FileUploadEntity.Model;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Builder
@Data
public class PodcastFile {
    private String name;
    private InputStream inputStream;
}
