package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.AddPostRequestDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.AddPostResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.findPostByIdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//Jest to serwis wykonujący operacje na repozytoriach mongo,

@Service
@RequiredArgsConstructor
public class PostFileEntityService {
    //trzeba ogarnąc repozytoria do GridFS oraz klasycznej kolekcji PostData

    public ResponseEntity<ListAllResponseDTO>listAllPosts(){
        //wyciąganie z bazy
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<findPostByIdResponseDTO> findPostById(ObjectId id){
        //wyciaganie z bazy
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<AddPostResponseDTO> addPost(AddPostRequestDTO addPostRequestDTO, MultipartFile file){
        //zapis do bazy pliku, pobranie objectID, zapisanie danych posta z id pliku
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
