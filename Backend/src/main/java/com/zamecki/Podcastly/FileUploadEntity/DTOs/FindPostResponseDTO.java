package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

//w obiekcie tej klasy zwracane są dane dotyczące posta, konieczne może być zdefiniowanie struktury JSON do tego aby plik też zwrócić

@Data
@Builder

public class FindPostResponseDTO {
    private String id;
    private String created_at;
    private String title;
    private String description;
    private String category;
    private List<String> tags;
    private String file_id;
}
