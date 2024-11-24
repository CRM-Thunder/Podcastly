package com.zamecki.Podcastly.FileUploadEntity;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class TikaValidator {
    static public boolean isMp4File(MultipartFile file) throws IOException{
        Tika tika=new Tika();
        String detectedType=tika.detect(file.getInputStream());
        return "video/mp4".equals(detectedType);
    }
}
