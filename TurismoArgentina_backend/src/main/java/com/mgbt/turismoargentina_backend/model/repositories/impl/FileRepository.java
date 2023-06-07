package com.mgbt.turismoargentina_backend.model.repositories.impl;

import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.model.repositories.IFileRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Repository
public class FileRepository implements IFileRepository {

    @Override
    public Resource getImageResource(String fileName, String principalDirectory, String finalDirectory) {
        Resource resource;
        try {
            Path filePath = this.getPath(fileName, principalDirectory, finalDirectory);
            resource = new UrlResource(filePath.toUri());
            return resource;
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public Resource getDefaultImageResource() {
        Resource resource;
        Path filePath = Paths.get("src/main/resources/static/images").resolve("no-image.jpg").toAbsolutePath();
        try {
            resource = new UrlResource(filePath.toUri());
            return resource;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String saveImage(MultipartFile file, String principalDirectory, String finalDirectory) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename().replace(" ", "");
        Path filePath = this.getPath(fileName, principalDirectory, finalDirectory);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }

    @Override
    public boolean deleteImage(String fileName, String principalDirectory, String finalDirectory) {
        if (fileName != null && fileName.length() > 0) {
            Path lastFilePath = this.getPath(fileName, principalDirectory, finalDirectory);
            File lastFile = lastFilePath.toFile();
            if (lastFile.exists() && lastFile.canRead()) return lastFile.delete();
        }
        return false;
    }

    private Path getPath(String fileName, String principalDirectory, String finalDirectory) {
        return Paths.get(principalDirectory + finalDirectory).resolve(fileName).toAbsolutePath();
    }
}
