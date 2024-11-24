package com.zamecki.Podcastly.FileUploadEntity.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


import java.util.Collection;
import java.util.Collections;
@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Override
    protected @NotNull String getDatabaseName() {
        return "Podcastly";
    }
    @Bean
    public @NotNull MongoClient mongoClient(){
        ConnectionString connectionString=new ConnectionString("mongodb+srv://damianzamecki:KEefiyfXIokYf4Zp@cluster0.9tptl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        MongoClientSettings mongoClientSettings=MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }
    @Override
    public @NotNull Collection <String> getMappingBasePackages(){
        return Collections.singleton("PostData");
    }
    @Bean
    @Primary
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext context, MongoCustomConversions conversions, MongoClient mongo) {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDatabaseFactory, context);
        converter.setCustomConversions(conversions);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Optional: to remove the _class field from documents return converter; }
        return converter;
    }

    @Bean
    @Primary
    public MongoDatabaseFactory mongoDatabaseFactory(@Autowired MongoClient mongoClient){
        return new SimpleMongoClientDatabaseFactory(mongoClient,getDatabaseName());
    }

    @Bean
    public @NotNull MongoTemplate mongoTemplate(@NotNull MongoDatabaseFactory mongoDatabaseFactory, @NotNull MappingMongoConverter mappingMongoConverter){
        return new MongoTemplate(mongoDatabaseFactory,  mappingMongoConverter);
    }
    @Bean
    public GridFsTemplate gridFsTemplate(@NotNull MongoDatabaseFactory mongoDatabaseFactory, MappingMongoConverter mappingMongoConverter) {
        return new GridFsTemplate(mongoDatabaseFactory,  mappingMongoConverter);
    }


}
