package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class AddPostRequestDTO{
    final String title;
    final String description;
    final String category;
    final List<String> tags;
}
