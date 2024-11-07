package com.zamecki.Podcastly.FileUploadEntity.Model;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

//Jest to klasa pracująca z kolekcją PostData, jest ona bezpośrednio zapisywana i odczytywana z bazy

@Document("PostData")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDataEntity {
    @Id
    private ObjectId id;
    private String created_at;
    private String modified_at;
    private String title;
    private String description;
    private String category;
    private List <String> tags;
    private ObjectId file_id;
}
