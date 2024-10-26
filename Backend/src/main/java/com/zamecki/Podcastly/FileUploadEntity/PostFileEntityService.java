package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.findPostByIdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostFileEntityService {
    //trzeba ogarnąc repozytoria do GridFS oraz klasycznej kolekcji PostData

    public ResponseEntity<ListAllResponseDTO>listAllPosts(){
        //wyciąganie z bazy
        return new ResponseEntity<>(HttpStatus.FOUND);
    }
    public ResponseEntity<findPostByIdResponseDTO> findPostById(ObjectId id){
        //wyciaganie z bazy
        return new ResponseEntity<>(HttpStatus.FOUND);
    }
}
