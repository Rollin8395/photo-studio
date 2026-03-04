package com.photo.controller;

import com.photo.entity.PhotoAsset;
import com.photo.repository.PhotoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    private final PhotoRepository photoRepository;

    public UploadController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @PostMapping
    public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {

        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(uploadDir + fileName);

        file.transferTo(dest);

        PhotoAsset photo = new PhotoAsset();
        photo.setOriginalFilename(file.getOriginalFilename());
        photo.setStoredFilename(fileName);
        photo.setFilePath(dest.getAbsolutePath());
        photo.setSizeBytes(file.getSize());

        photoRepository.save(photo);

        return "Uploaded successfully";
    }
}