package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public record AddPostRequestDTO(String title, String description, String category, List<String> tags) {
}
