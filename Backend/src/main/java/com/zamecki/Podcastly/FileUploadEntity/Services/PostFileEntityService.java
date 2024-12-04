package com.zamecki.Podcastly.FileUploadEntity.Services;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.DTOConverter;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.AllUpdateFieldsAreNullException;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.FileTypeViolationException;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.PostNotSavedException;
import com.zamecki.Podcastly.FileUploadEntity.Model.PodcastFile;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import com.zamecki.Podcastly.FileUploadEntity.Repositories.GridsFSRepository;
import com.zamecki.Podcastly.FileUploadEntity.Repositories.MongoTemplateRepository;
import com.zamecki.Podcastly.FileUploadEntity.TikaValidation.TikaValidator;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<FindPostResponseDTO> findPostById(String id){
        PostDataEntity dbPostDataEntity= postDataRepository.findById(id);
        if(dbPostDataEntity==null){
            throw new ObjectNotFoundException("Post not found!");
        }
        FindPostResponseDTO findPostResponseDTO=DTOConverter.FindPostByIdToDtoConv(dbPostDataEntity);
        return new ResponseEntity<>(findPostResponseDTO, HttpStatus.OK);
    }
    public ResponseEntity<List<ListAllResponseDTO>> listAllByCategory(String category){
        List<PostDataEntity>dbPostDataEntityList=mongoTemplateRepository.findAllByCategory(category);
        if(!dbPostDataEntityList.isEmpty()){
            List<ListAllResponseDTO> listAllByCategoryDTOList = dbPostDataEntityList.stream().map(DTOConverter::ListAllToDtoConv).toList();
            return new ResponseEntity<>(listAllByCategoryDTOList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<List<ListAllResponseDTO>> listAllByTitleContaining(String title){
        List<PostDataEntity> dbPostDataEntityList=mongoTemplateRepository.findAllByTitleContaining(title);
        if(!dbPostDataEntityList.isEmpty()){
            List<ListAllResponseDTO> listAllByTitleDTOList=dbPostDataEntityList.stream().map(DTOConverter::ListAllToDtoConv).toList();
            return new ResponseEntity<>(listAllByTitleDTOList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public PodcastFile getPodcastFile(String id) throws IOException {
        return gridsFSRepository.getPodcastFile(id);
    }
    public ResponseEntity<AddPostResponseDTO> addPost(AddPostRequestDTO addPostRequestDTO, MultipartFile file) throws IOException {
        if (!TikaValidator.isMp4File(file)){
            throw new FileTypeViolationException("Uploaded file is not mp4!");
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
            throw new PostNotSavedException("Post could not be added!");
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
                throw new AllUpdateFieldsAreNullException("All fields are null!");
            }
        }
        else{
            throw new FileTypeViolationException("Uploaded file is not mp4!");
        }
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(updatePostRequestDTO.getId()).message("Podcast has been updated!").build() ,HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> deletePost(String id){

         PostDataEntity dbPostDataEntity= postDataRepository.findById(id);
         if(dbPostDataEntity==null){
             throw new ObjectNotFoundException("Post not found!");
         }
         if (dbPostDataEntity.getFile_id()!=null){
             gridsFSRepository.deletePodcastFile(dbPostDataEntity.getFile_id());
         }
        postDataRepository.deleteById(id);
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(id).message("Podcast has been deleted!").build(), HttpStatus.OK);
    }
}
