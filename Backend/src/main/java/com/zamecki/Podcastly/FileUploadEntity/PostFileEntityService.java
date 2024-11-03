package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Jest to serwis wykonujący operacje na repozytoriach mongo,

@Service
@RequiredArgsConstructor
public class PostFileEntityService {
    //trzeba ogarnąc repozytoria do GridFS oraz klasycznej kolekcji PostData

    public ResponseEntity<List<ListAllResponseDTO>>listAllPosts(){
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
    public ResponseEntity<AddPostResponseDTO> updatePost(UpdatePostRequestDTO updatePostRequestDTO, MultipartFile file){
        //wyciągamy oryginał z bazy danych, ustawiamy wartości, które są inne z dto do obiektu bazodanowego i z powrotem go zamieszczamy, jeżeli plik jest inny to najpierw zapisujemy plik i wyciągamy nowy id, usuwamy stary plik
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<String> deletePost(ObjectId id){
        //usuwamy posta z bazy, usuwamy też plik
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
