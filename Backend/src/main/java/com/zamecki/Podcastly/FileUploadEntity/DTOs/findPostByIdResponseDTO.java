package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

//w obiekcie tej klasy zwracane są dane dotyczące posta, konieczne może być zdefiniowanie struktury JSON do tego aby plik też zwrócić

@Data
@Builder

public class findPostByIdResponseDTO {
    private String id;
    private String created_at;
    private String modified_at;
    private String title;
    private String description;
    private String category;
    private List<String> tags;
    private MultipartFile file;
}
