package com.zamecki.Podcastly.FileUploadEntity.TikaValidation;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

public class TikaValidator {
    static public boolean isMp4File(MultipartFile file) throws IOException{
        Tika tika=new Tika();
        String detectedType=tika.detect(file.getInputStream());
        System.out.println(detectedType);
        return Objects.equals(detectedType, "video/mp4") || Objects.equals(detectedType, "audio/mp4") || Objects.equals(detectedType, "video/quicktime");
    }
}
