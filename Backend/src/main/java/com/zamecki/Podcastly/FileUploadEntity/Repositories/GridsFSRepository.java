package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.ObjectNotFoundException;
import com.zamecki.Podcastly.FileUploadEntity.Model.PodcastFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GridsFSRepository {
    private final GridFsTemplate gridFsTemplate;

    public String addPodcastFile(MultipartFile file) throws IOException {
        DBObject metaData=new BasicDBObject();
        metaData.put("type",file.getContentType());
        metaData.put("filename",file.getOriginalFilename());
        ObjectId id=gridFsTemplate.store(file.getInputStream(),file.getOriginalFilename(),file.getContentType(),metaData);
        return id.toString();
    }
    public PodcastFile getPodcastFile(String file_id) throws IOException {
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(file_id));
        GridFSFile dbFile=gridFsTemplate.findOne(query);
        if(dbFile==null) {
            throw new ObjectNotFoundException("File not found!");
        }else{
            //TODO: Zrobić weryfikację czy są metadane, jak nie to wpisywać nulle
            return PodcastFile.builder().name( dbFile.getMetadata().get("filename").toString()).inputStream(gridFsTemplate.getResource(dbFile).getInputStream()).contentType(dbFile.getMetadata().get("_contentType").toString()).build();
        }
    }
    public void deletePodcastFile(String file_id){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(file_id));
        gridFsTemplate.delete(query);
    }

}
