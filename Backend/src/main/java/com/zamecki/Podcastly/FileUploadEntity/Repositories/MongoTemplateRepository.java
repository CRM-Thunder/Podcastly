package com.zamecki.Podcastly.FileUploadEntity.Repositories;

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
        query.fields().include("id").include("title").include("created_at").include("category").include("tags");
        return mongoTemplate.find(query, PostDataEntity.class, "PostData"); }
    public PostDataEntity findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.fields().include("id").include("created_at").include("modified_at").include("title").include("description").include("category").include("tags");
        return mongoTemplate.findOne(query, PostDataEntity.class, "PostData");
    }
    public PostDataEntity addPost(PostDataEntity postDataEntity){
        return mongoTemplate.save(postDataEntity);
    }
    public void deleteById(String id){
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query,PostDataEntity.class, "PostData");
    }
}
