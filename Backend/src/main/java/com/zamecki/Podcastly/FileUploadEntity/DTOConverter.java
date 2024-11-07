package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor

public class DTOConverter {

    public static ListAllResponseDTO ListAllToDtoConv(@NotNull PostDataEntity postDataEntity){
        return ListAllResponseDTO.builder().id(postDataEntity.getId().toString()).title(postDataEntity.getTitle()).created_at(postDataEntity.getCreated_at()).category(postDataEntity.getCategory()).tags(postDataEntity.getTags()).build();
    }

}
