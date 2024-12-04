package com.zamecki.Podcastly.FileUploadEntity.Controllers;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import com.zamecki.Podcastly.FileUploadEntity.Model.PodcastFile;
import com.zamecki.Podcastly.FileUploadEntity.Services.PostFileEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//Kontroler obsługujący requesty
@CrossOrigin(origins = "http://localhost:63343")
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
    public ResponseEntity<FindPostResponseDTO> findPostById(@PathVariable String id) throws IOException {
        return postFileEntityService.findPostById(id);
    }
    @GetMapping("/find/category/{category}")
    public ResponseEntity<List<ListAllResponseDTO>> listAllByCategory(@PathVariable String category) throws IOException {
        return postFileEntityService.listAllByCategory(category);
    }
    @GetMapping("/find/title/{title}")
    public ResponseEntity<List<ListAllResponseDTO>> listAllByTitle(@PathVariable String title) throws IOException {
        return postFileEntityService.listAllByTitleContaining(title);
    }

    @GetMapping("/stream/{id}")
    public ResponseEntity<InputStreamResource> streamPodcastFile(@PathVariable String id) throws IOException {
        PodcastFile podcastFile=postFileEntityService.getPodcastFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(podcastFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\""+podcastFile.getName()+"\"")
                .body(new InputStreamResource(podcastFile.getInputStream()));
    }
    @PostMapping("/add")
    public ResponseEntity<AddPostResponseDTO> addPost(@NotNull @RequestPart("addPostRequestDTO") AddPostRequestDTO addPostRequestDTO, @RequestPart (value = "file") MultipartFile file) throws IOException {

        return postFileEntityService.addPost(addPostRequestDTO, file);
    }
    @PutMapping("/update")
    public ResponseEntity<AddPostResponseDTO> updatePost(@RequestPart("updatePostRequestDTO") @Valid UpdatePostRequestDTO updatePostRequestDTO, @RequestPart (value = "file", required = false) MultipartFile file) throws IOException {
        return postFileEntityService.updatePost(updatePostRequestDTO, file);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AddPostResponseDTO> deletePost (@PathVariable String id){
        return postFileEntityService.deletePost(id);
    }

}
