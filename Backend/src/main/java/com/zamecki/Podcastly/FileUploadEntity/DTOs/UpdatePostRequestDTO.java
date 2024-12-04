package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import java.util.List;
@Getter
@Setter
@AllArgsConstructor

public class UpdatePostRequestDTO {
        @NotNull(message = "Id can't be empty!")
        @Pattern(regexp = "(?i)^[a-f0-9]{24}$", message = "Invalid id format!")
        private String id;
        @Length(min = 3, max = 64, message = "Too short or too long title!")
        private String title;
        private String description;
        @Length(min = 5, max = 20, message = "Too short or too long category name!")
        private String category;
        private List<String> tags;
}
