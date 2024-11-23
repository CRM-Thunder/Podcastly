package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import lombok.Builder;
import lombok.Data;
import java.util.List;

//W obiekcie tej klasy zwracane są dane dla endpointa listAll

@Data
@Builder

public class ListAllResponseDTO {
    private String id;
    private String title;
    private String created_at;
    private String category;
    private List<String> tags;
}
