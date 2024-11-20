package com.zamecki.Podcastly.FileUploadEntity.Repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GridsFSRepository {
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;
}
