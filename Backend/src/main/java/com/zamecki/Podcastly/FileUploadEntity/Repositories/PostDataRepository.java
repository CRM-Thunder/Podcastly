package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("mongodb")
public interface PostDataRepository extends MongoRepository<PostDataEntity, ObjectId> {
    @NotNull
    @Query(value = "{}", fields = "{ 'modified_at': 0, 'description' : 0, 'file_id' : 0}")
    List<PostDataEntity> findAll();
    @NotNull
    Optional<PostDataEntity> findById(@NotNull String id);
    void deleteById(@NotNull String id);

}
