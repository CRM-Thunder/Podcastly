package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Kontroler obsługujący requesty

@Controller
@RequiredArgsConstructor
@RequestMapping("/rest/podcasts")
public class PostFileEntityController {

    private final PostFileEntityService postFileEntityService;

    @GetMapping("/listall")
    public ResponseEntity<List<ListAllResponseDTO>> listAllPosts(){
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
    @PutMapping("/update")
    public ResponseEntity<AddPostResponseDTO> updatePost(@RequestParam UpdatePostRequestDTO updatePostRequestDTO, @RequestPart (value = "file") MultipartFile file){
        return postFileEntityService.updatePost(updatePostRequestDTO, file);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost (@PathVariable ObjectId id){
        return postFileEntityService.deletePost(id);
    }

}
