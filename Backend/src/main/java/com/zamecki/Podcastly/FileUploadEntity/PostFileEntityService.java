package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import com.zamecki.Podcastly.FileUploadEntity.Model.PodcastFile;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import com.zamecki.Podcastly.FileUploadEntity.Repositories.GridsFSRepository;
import com.zamecki.Podcastly.FileUploadEntity.Repositories.MongoTemplateRepository;
import com.zamecki.Podcastly.FileUploadEntity.exceptions.ContentTypeViolation;
import com.zamecki.Podcastly.FileUploadEntity.exceptions.EmptyUpdateViolation;
import com.zamecki.Podcastly.FileUploadEntity.exceptions.PostNotAddedException;
import com.zamecki.Podcastly.FileUploadEntity.exceptions.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

//Jest to serwis wykonujący operacje na repozytoriach mongo,

@Service
@RequiredArgsConstructor
public class PostFileEntityService {
    private final MongoTemplateRepository postDataRepository;
    private final GridsFSRepository gridsFSRepository;
    private final MongoTemplateRepository mongoTemplateRepository;

    public ResponseEntity<List<ListAllResponseDTO>>listAllPosts(){
        List<PostDataEntity> dbPostDataEntityList=postDataRepository.findAll();
        List<ListAllResponseDTO> listAllResponseDTOList=dbPostDataEntityList.stream().map(DTOConverter::ListAllToDtoConv).toList();
        if(!listAllResponseDTOList.isEmpty()) {
            return new ResponseEntity<>(listAllResponseDTOList,HttpStatus.OK);
        }
        else{
            //wyjątek rzucimy potem jak dodam obsługę xD
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<FindPostByIdResponseDTO> findPostById(String id){
        //wyciaganie z bazy
        PostDataEntity dbPostDataEntity= postDataRepository.findById(id);
        if(dbPostDataEntity==null){
            throw new PostNotFoundException("Post not found!");
        }
        //wyszukanie pliku po id
        //PodcastFile podcastFile=gridsFSRepository.getPodcastFile(dbPostDataEntity.getFile_id());
        FindPostByIdResponseDTO findPostByIdResponseDTO=DTOConverter.FindPostByIdToDtoConv(dbPostDataEntity);
        return new ResponseEntity<>(findPostByIdResponseDTO, HttpStatus.OK);
    }
    public PodcastFile getPodcastFile(String id) throws IOException {
        return gridsFSRepository.getPodcastFile(id);
    }
    public ResponseEntity<AddPostResponseDTO> addPost(AddPostRequestDTO addPostRequestDTO, MultipartFile file) throws IOException {
        if (!TikaValidator.isMp4File(file)){
            throw new ContentTypeViolation("Uploaded file is not mp4!");
        }
        String file_id=gridsFSRepository.addPodcastFile(file);
        CustomDate date=new CustomDate();
        PostDataEntity postDataEntity=PostDataEntity.builder()
                .id(ObjectId.get())
                .created_at(date.getDateTime())
                .modified_at(date.getDateTime())
                .title(addPostRequestDTO.getTitle())
                .description(addPostRequestDTO.getDescription())
                .category(addPostRequestDTO.getCategory())
                .tags(addPostRequestDTO.getTags())
                .file_id(file_id)
                .build();
        PostDataEntity savedEntity=postDataRepository.addPost(postDataEntity);
        if(savedEntity==null){
            throw new PostNotAddedException("Post could not be added!");
        }
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(postDataEntity.getId().toString()).message("New podcast has been added!").build(),HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> updatePost(UpdatePostRequestDTO updatePostRequestDTO, MultipartFile file) throws IOException {
        if(file!=null && TikaValidator.isMp4File(file)){
            PostDataEntity dbPostDataEntity=postDataRepository.findById(updatePostRequestDTO.getId());
            gridsFSRepository.deletePodcastFile(dbPostDataEntity.getFile_id());
            String file_id=gridsFSRepository.addPodcastFile(file);
            mongoTemplateRepository.updatePost(updatePostRequestDTO, file_id);
        }
        else if(file==null){
            if (updatePostRequestDTO.getTitle()!=null || updatePostRequestDTO.getDescription()!=null || updatePostRequestDTO.getCategory()!=null||updatePostRequestDTO.getTags()!=null){
                mongoTemplateRepository.updatePost(updatePostRequestDTO, null);
            }else{
                throw new EmptyUpdateViolation("All fields are null!");
            }
        }
        else{
            throw new ContentTypeViolation("Uploaded file is not mp4!");
            //TODO: WYWALIĆ WSZYSTKIE CUSTOMOWE RUNTIME WYJĄTKI I ZROBIĆ 1 GŁÓWNY RuntimeException, tylko inne message ustawiać i inny kod http
        }
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(updatePostRequestDTO.getId()).message("Podcast has been updated!").build() ,HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> deletePost(String id){

         PostDataEntity dbPostDataEntity= postDataRepository.findById(id);
         if(dbPostDataEntity==null){
             throw new PostNotFoundException("Post not found!");
         }
         if (dbPostDataEntity.getFile_id()!=null){
             gridsFSRepository.deletePodcastFile(dbPostDataEntity.getFile_id());
         }
        postDataRepository.deleteById(id);
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(id).message("Podcast has been deleted!").build(), HttpStatus.OK);
    }
}
