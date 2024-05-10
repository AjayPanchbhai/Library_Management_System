package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is Empty. File field shouldn't be Empty!");
        }

        if (!Objects.equals(file.getContentType(), "image/jpeg")) {
            return ResponseEntity.badRequest().body("Only image/jpeg files are Allowed!");
        }

        boolean isUploaded = fileUploadHelper.uploadFile(file);

        if (isUploaded) {
            return ResponseEntity.ok().body(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").
                    path(Objects.requireNonNull(file.getOriginalFilename())).toUriString());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }
}
