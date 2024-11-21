package com.zamecki.Podcastly.FileUploadEntity.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
@Data
@Builder
public class PodcastFile {
    private String name;
    private InputStream inputStream;
}
