package com.zamecki.Podcastly.FileUploadEntity.GridFSConfiguration;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = GridFSConfiguration.class)
public class GridFSConfiguration extends AbstractMongoClientConfiguration {
    @Autowired
    private MappingMongoConverter mappingMongoConverter;
    @Bean public GridFsTemplate gridFsTemplate() {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter);
    }

    @Override
    protected @NotNull String getDatabaseName() {
        return "Podcastly";
    }
}
