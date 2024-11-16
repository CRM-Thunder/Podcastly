package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import com.zamecki.Podcastly.FileUploadEntity.Model.PostDataEntity;
import com.zamecki.Podcastly.FileUploadEntity.Repositories.PostDataRepository;
import com.zamecki.Podcastly.FileUploadEntity.exceptions.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

//Jest to serwis wykonujący operacje na repozytoriach mongo,

@Service
@RequiredArgsConstructor
public class PostFileEntityService {
    //trzeba ogarnąc repozytoria do GridFS
    PostDataRepository postDataRepository;

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
    public ResponseEntity<FindPostByIdResponseDTO> findPostById(String id) throws PostNotFoundException {
        //wyciaganie z bazy
         Optional<PostDataEntity> dbPostDataEntity= Optional.ofNullable(postDataRepository.findById(id).orElseThrow(()->new PostNotFoundException("Post not found!")));
        FindPostByIdResponseDTO findPostByIdResponseDTO=DTOConverter.FindPostByIdToDtoConv(dbPostDataEntity.get());
        return new ResponseEntity<>(findPostByIdResponseDTO, HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> addPost(AddPostRequestDTO addPostRequestDTO, MultipartFile file){
        //najpierw zapisujemy plik, ale generuję mu sam id
        //dodanie pliku, wyciągnięcie id itd...
        CustomDate date=new CustomDate();
        PostDataEntity postDataEntity=PostDataEntity.builder()
                .id(ObjectId.get())
                .created_at(date.getDateTime())
                .modified_at(date.getDateTime())
                .title(addPostRequestDTO.getTitle())
                .description(addPostRequestDTO.getDescription())
                .category(addPostRequestDTO.getCategory())
                .tags(addPostRequestDTO.getTags())
                .file_id(ObjectId.get())
                .build();
        //walidacja musi być czy zostało zapisane
        postDataRepository.save(postDataEntity);
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(postDataEntity.getId().toString()).message("New podcast has been added!").build(),HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> updatePost(UpdatePostRequestDTO updatePostRequestDTO, MultipartFile file){
        //wyciągamy oryginał z bazy danych, ustawiamy wartości, które są inne z dto do obiektu bazodanowego i z powrotem go zamieszczamy, jeżeli plik jest inny to najpierw zapisujemy plik i wyciągamy nowy id, usuwamy stary plik
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> deletePost(String id) throws PostNotFoundException {
        //usuwamy posta z bazy, usuwamy też plik
        Optional<PostDataEntity> dbPostDataEntity=Optional.ofNullable(postDataRepository.findById(id).orElseThrow(()->new PostNotFoundException("Post not found!")));
        //najpierw usuwamy plik:
        String fileId=dbPostDataEntity.get().getFile_id().toString();
        //gridfs repo-> usuwa
        //po usunięciu usuwamy dane posta
        postDataRepository.deleteById(id);
        return new ResponseEntity<>(AddPostResponseDTO.builder().id(id).message("Podcast has been deleted!").build(), HttpStatus.OK);
    }
}
