package com.zamecki.Podcastly.FileUploadEntity.DTOs;

import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor

public class DTOConverter {

    public static ListAllResponseDTO ListAllToDtoConv(@NotNull PostDataEntity postDataEntity){
        return ListAllResponseDTO.builder().id(postDataEntity.getId().toString()).title(postDataEntity.getTitle()).created_at(postDataEntity.getCreated_at()).category(postDataEntity.getCategory()).tags(postDataEntity.getTags()).build();
    }

    public static FindPostResponseDTO FindPostByIdToDtoConv(@NotNull PostDataEntity postDataEntity){
        return FindPostResponseDTO.builder().id(postDataEntity.getId().toString()).title(postDataEntity.getTitle()).description(postDataEntity.getDescription()).created_at(postDataEntity.getCreated_at()).category(postDataEntity.getCategory()).tags(postDataEntity.getTags()).file_id(postDataEntity.getFile_id()).build();
    }

}
