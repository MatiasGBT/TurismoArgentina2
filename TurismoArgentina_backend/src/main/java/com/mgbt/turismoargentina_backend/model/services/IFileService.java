package com.mgbt.turismoargentina_backend.model.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;

@Service
public interface IFileService {
    ResponseEntity<Resource> getPhoto(String fileName, String finalDirectory);
    String save(MultipartFile file, String finalDirectory) throws IOException;
    Boolean delete(String fileName, String directory);
    Path getPath(String fileName, String finalDirectory);
}
