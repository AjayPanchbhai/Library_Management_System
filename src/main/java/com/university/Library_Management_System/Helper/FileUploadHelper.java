package com.university.Library_Management_System.Helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadHelper.class);

    // STATIC PATH
    //public final String UPLOAD_DIR = "G:\\Library_Management_System\\Library_Management_System\\src\\main\\resources\\static\\images";

    //DYNAMIC PATH
    public final String UPLOAD_DIR =  new ClassPathResource("static/images").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public Boolean uploadFile(MultipartFile multipartFile) {
        boolean isUpload = false;
        try {
            Path destination = Paths.get(UPLOAD_DIR, multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            logger.error("Error occurred while uploading file: {}", e.getMessage());
        }

        return isUpload;
    }
}
