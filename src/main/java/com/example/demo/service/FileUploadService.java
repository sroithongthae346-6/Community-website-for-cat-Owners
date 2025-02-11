package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        try {
            // Create upload directory if it does not exist
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Generate a unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDirFile, fileName);

            // Save the file
            file.transferTo(destinationFile);

            // Return the file path
            return Paths.get(uploadDir, fileName).toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file.");
        }
    }
}
