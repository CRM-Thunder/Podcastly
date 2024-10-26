package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.findPostByIdResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.AddPostResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.AddPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//Kontroler obsługujący requesty

@Controller
@RequiredArgsConstructor
@RequestMapping("/rest/podcasts")
public class PostFileEntityController {

    private final PostFileEntityService postFileEntityService;

    @GetMapping("/listall")
    public ResponseEntity<ListAllResponseDTO> listAllPosts(){
        return postFileEntityService.listAllPosts();
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<findPostByIdResponseDTO> findPostById(@PathVariable ObjectId id){
        return postFileEntityService.findPostById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<AddPostResponseDTO> addPost(@RequestParam AddPostRequestDTO addPostRequestDTO, @RequestPart (value = "file") MultipartFile file){
        return postFileEntityService.addPost(addPostRequestDTO, file);
    }


}
