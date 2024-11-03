package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;
@Getter
@RequiredArgsConstructor

public class UpdatePostRequestDTO {
        final ObjectId id;
        final String title;
        final String description;
        final String category;
        final List<String> tags;
}
