package com.zamecki.Podcastly.FileUploadEntity.Controllers;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.*;
import com.zamecki.Podcastly.FileUploadEntity.Model.PodcastFile;
import com.zamecki.Podcastly.FileUploadEntity.Services.PostFileEntityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//Kontroler obsługujący requesty
@CrossOrigin(origins = "http://localhost")
@Controller
@RequiredArgsConstructor
@RequestMapping("/rest/podcasts")
@Validated
public class PostFileEntityController {

    private final PostFileEntityService postFileEntityService;

    @GetMapping("/listall")
    public ResponseEntity<List<ListAllResponseDTO>> listAllPosts(){
        return postFileEntityService.listAllPosts();
    }

    @GetMapping("/findById")
    public ResponseEntity<FindPostResponseDTO> findPostById(@RequestParam(value = "id") @NotBlank(message = "Parameter: id can't be empty") @Pattern(regexp = "(?i)^[a-f0-9]{24}$", message = "Invalid id format!") String id) throws IOException {
        return postFileEntityService.findPostById(id);
    }

    @GetMapping("/findByCat")
    public ResponseEntity<List<ListAllResponseDTO>> listAllByCategory(@RequestParam(value = "category") @NotBlank(message = "Parameter: category can't be empty") @Pattern(regexp = "(?i)[a-z]+", message = "Invalid category pattern!") @Length(min = 5, max = 20, message = "Too short or too long category name!") String category) throws IOException {
        return postFileEntityService.listAllByCategory(category);
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<ListAllResponseDTO>> listAllByTitle(@RequestParam(value="title") @NotBlank(message = "Parameter: title can't be empty") String title) throws IOException {
        return postFileEntityService.listAllByTitleContaining(title);
    }

    @GetMapping("/stream")
    public ResponseEntity<InputStreamResource> streamPodcastFile(@RequestParam(value = "id") @NotBlank(message = "Parameter: id can't be empty") @Pattern(regexp = "(?i)^[a-f0-9]{24}$", message = "Invalid id format!") String id) throws IOException {
        PodcastFile podcastFile=postFileEntityService.getPodcastFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(podcastFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\""+podcastFile.getName()+"\"")
                .body(new InputStreamResource(podcastFile.getInputStream()));
    }

    @PostMapping("/add")
    public ResponseEntity<AddPostResponseDTO> addPost(@NotNull(message = "Post data can't be empty!") @RequestPart("addPostRequestDTO") @Valid AddPostRequestDTO addPostRequestDTO, @RequestPart (value = "file") MultipartFile file) throws IOException {

        return postFileEntityService.addPost(addPostRequestDTO, file);
    }

    @PutMapping("/update")
    public ResponseEntity<AddPostResponseDTO> updatePost(@NotNull(message = "Post data can't be empty!") @RequestPart("updatePostRequestDTO") @Valid UpdatePostRequestDTO updatePostRequestDTO, @RequestPart (value = "file", required = false) MultipartFile file) throws IOException {
        return postFileEntityService.updatePost(updatePostRequestDTO, file);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<AddPostResponseDTO> deletePost (@RequestParam(value = "id") @NotBlank(message = "Parameter: id can't be empty!") @Pattern(regexp = "(?i)^[a-f0-9]{24}$", message = "Invalid id format!") String id){
        return postFileEntityService.deletePost(id);
    }

}
