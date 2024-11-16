package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.FindPostByIdResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor

public class DTOConverter {

    public static ListAllResponseDTO ListAllToDtoConv(@NotNull PostDataEntity postDataEntity){
        return ListAllResponseDTO.builder().id(postDataEntity.getId().toString()).title(postDataEntity.getTitle()).created_at(postDataEntity.getCreated_at()).category(postDataEntity.getCategory()).tags(postDataEntity.getTags()).build();
    }
    //dodać plik po implementacji gridfs
    public static FindPostByIdResponseDTO FindPostByIdToDtoConv(@NotNull PostDataEntity postDataEntity){
        return FindPostByIdResponseDTO.builder().id(postDataEntity.getId().toString()).title(postDataEntity.getTitle()).description(postDataEntity.getDescription()).created_at(postDataEntity.getCreated_at()).modified_at(postDataEntity.getModified_at()).category(postDataEntity.getCategory()).tags(postDataEntity.getTags()).build();
    }

}
