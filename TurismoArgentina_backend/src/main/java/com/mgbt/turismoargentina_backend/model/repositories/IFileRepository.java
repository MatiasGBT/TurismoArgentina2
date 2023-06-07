package com.mgbt.turismoargentina_backend.model.repositories;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public interface IFileRepository {
    Resource getImageResource(String fileName, String principalDirectory, String finalDirectory);
    Resource getDefaultImageResource();
    String saveImage(MultipartFile file, String principalDirectory, String finalDirectory) throws IOException;
    boolean deleteImage(String fileName, String principalDirectory, String finalDirectory);
}
