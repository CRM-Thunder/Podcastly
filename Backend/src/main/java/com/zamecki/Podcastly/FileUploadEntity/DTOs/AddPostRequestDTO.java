package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class AddPostRequestDTO{
     private String title;
     private String description;
     private String category;
     private List<String> tags;
}
