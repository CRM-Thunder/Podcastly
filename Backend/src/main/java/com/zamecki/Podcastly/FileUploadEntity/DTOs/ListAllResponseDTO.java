package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder

public class ListAllResponseDTO {
    private ObjectId id;
    private String podcast_title;
    private CustomDate createdAt;
}
