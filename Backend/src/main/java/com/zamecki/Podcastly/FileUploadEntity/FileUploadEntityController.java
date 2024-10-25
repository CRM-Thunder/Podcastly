package com.zamecki.Podcastly.FileUploadEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rest/podcasts")
public class FileUploadEntityController {

    private final FileUploadEntityService fileUploadEntityService;




}
