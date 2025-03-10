package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.UpdatePostRequestDTO;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoTemplateRepository {
    private final MongoTemplate mongoTemplate;
    public List<PostDataEntity> findAll() {
        Query query = new Query();
        query.fields().include("id","title","created_at","category","tags");
        return mongoTemplate.find(query, PostDataEntity.class, "PostData");
    }
    public PostDataEntity findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.fields().exclude("modified_at");
        return mongoTemplate.findOne(query, PostDataEntity.class, "PostData");
    }
    public List<PostDataEntity> findAllByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").regex(category,"i"));
        query.fields().include("id","title","created_at","category","tags");
        return mongoTemplate.find(query, PostDataEntity.class, "PostData");
    }
    public List<PostDataEntity> findAllByTitleContaining(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title,"i"));
        return mongoTemplate.find(query, PostDataEntity.class, "PostData");
    }
    public PostDataEntity addPost(PostDataEntity postDataEntity){
        return mongoTemplate.save(postDataEntity);
    }
    public void deleteById(String id){
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query,PostDataEntity.class, "PostData");
    }
    public void updatePost(UpdatePostRequestDTO updatePostRequestDTO, String file_id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updatePostRequestDTO.getId()));
        Update update = new Update();
        if(updatePostRequestDTO.getTitle() != null){
            update.set("title", updatePostRequestDTO.getTitle());
        }
        if(updatePostRequestDTO.getDescription() != null){
            update.set("description", updatePostRequestDTO.getDescription());
        }
        if(updatePostRequestDTO.getCategory() != null){
            update.set("category", updatePostRequestDTO.getCategory());
        }
        if(updatePostRequestDTO.getTags() != null){
            update.set("tags", updatePostRequestDTO.getTags());
        }
        if (file_id!=null){
            update.set("file_id", file_id);
        }
        CustomDate date=new CustomDate();
        update.set("modified_at",date.getDateTime());
        mongoTemplate.updateFirst(query,update,PostDataEntity.class);
    }
}
