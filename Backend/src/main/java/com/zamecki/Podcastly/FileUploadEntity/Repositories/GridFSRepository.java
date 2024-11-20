package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import com.zamecki.Podcastly.FileUploadEntity.Model.FileMetadata;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class GridFSRepository {
    private final GridFsOperations gridFsOperations;
    public ObjectId store(MultipartFile file) {
        FileMetadata fileMetadata = FileMetadata.builder().filename(file.getName()).size(file.getSize()).contentType(file.getContentType()).build();

    }
}
