package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import com.zamecki.Podcastly.FileUploadEntity.Model.Video;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

//w obiekcie tej klasy zwracane są dane dotyczące posta, konieczne może być zdefiniowanie struktury JSON do tego aby plik też zwrócić

@Data
@Builder

public class FindPostByIdResponseDTO {
    private String id;
    private String created_at;
    private String modified_at;
    private String title;
    private String description;
    private String category;
    private List<String> tags;
    private Video file;
}