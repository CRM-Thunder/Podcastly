package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<FindPostByIdResponseDTO> findPostById(@PathVariable String id) throws IOException {
        return postFileEntityService.findPostById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<AddPostResponseDTO> addPost(@NotNull @RequestPart("addPostRequestDTO") AddPostRequestDTO addPostRequestDTO, @RequestPart (value = "file", required = false) MultipartFile file) throws IOException {

        return postFileEntityService.addPost(addPostRequestDTO, file);
    }
    @PutMapping("/update")
    public ResponseEntity<AddPostResponseDTO> updatePost(@RequestPart("updatePostRequestDTO") UpdatePostRequestDTO updatePostRequestDTO, @RequestPart (value = "file", required = false) MultipartFile file){
        return postFileEntityService.updatePost(updatePostRequestDTO, file);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AddPostResponseDTO> deletePost (@PathVariable String id){
        return postFileEntityService.deletePost(id);
    }

}
