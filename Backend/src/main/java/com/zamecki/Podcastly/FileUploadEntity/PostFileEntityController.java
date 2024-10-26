package com.zamecki.Podcastly.FileUploadEntity;

import com.zamecki.Podcastly.FileUploadEntity.DTOs.ListAllResponseDTO;
import com.zamecki.Podcastly.FileUploadEntity.DTOs.findPostByIdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
