package com.zamecki.Podcastly.FileUploadEntity.Model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@Builder
@Data
public class Video {
    private String name;
    private InputStream inputStream;
}
