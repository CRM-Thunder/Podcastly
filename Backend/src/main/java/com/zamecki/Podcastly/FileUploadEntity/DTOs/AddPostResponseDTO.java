package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Builder;
import lombok.Data;
//W obiekcie tej klasy zwracamy wiadomość, że udało się dodać post, a także jego id

@Data
@Builder
public class AddPostResponseDTO {
    private String message;
    private String id;
}
