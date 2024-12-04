package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AddPostRequestDTO{
     @NotNull(message = "Title can't be empty!")
     @Length(min = 3, max = 64, message = "Too short or too long title!")
     private String title;
     @NotNull(message = "Description can't be empty!")
     private String description;
     @NotNull(message = "Category can't be empty!")
     @Pattern(regexp = "(?i)[a-z]+", message = "Invalid category pattern!")
     @Length(min = 5, max = 20, message = "Too short or too long category name!")
     private String category;
     private List<String> tags;
}
