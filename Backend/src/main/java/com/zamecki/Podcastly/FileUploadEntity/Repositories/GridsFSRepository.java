package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GridsFSRepository {
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    public String addPodcastFile(MultipartFile file) throws IOException {
        DBObject metaData=new BasicDBObject();
        metaData.put("type",file.getContentType());
        metaData.put("filename",file.getName());
        ObjectId id=gridFsTemplate.store(file.getInputStream(),file.getName(),file.getContentType(),metaData);
        return id.toString();
    }
}
