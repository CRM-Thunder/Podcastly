package com.zamecki.Podcastly.FileUploadEntity.Model;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PostData")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostFileEntity {
    @Id
    private ObjectId id;
    private CustomDate createdAt;
    private CustomDate modifiedAt;
    private String description;
    private String place;
    //albo file_id albo sam plik, zobaczymy jaka architektura mi wyjdzie
    //private ObjectId file_id;
}
