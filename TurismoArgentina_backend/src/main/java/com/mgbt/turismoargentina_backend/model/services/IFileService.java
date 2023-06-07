package com.mgbt.turismoargentina_backend.model.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public interface IFileService {
    Resource getPhoto(String fileName, String finalDirectory);
    String save(MultipartFile file, String finalDirectory) throws IOException;
    boolean delete(String fileName, String directory);
}
