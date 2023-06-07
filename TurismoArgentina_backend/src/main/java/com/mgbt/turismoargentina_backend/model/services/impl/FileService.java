package com.mgbt.turismoargentina_backend.model.services.impl;

import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.model.repositories.IFileRepository;
import com.mgbt.turismoargentina_backend.model.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@Service
public class FileService implements IFileService {
    private final static String PRINCIPAL_DIRECTORY = "images";

    @Autowired
    private IFileRepository fileRepository;

    @Override
    public Resource getPhoto(String fileName, String finalDirectory) {
        Resource resource = fileRepository.getImageResource(fileName, PRINCIPAL_DIRECTORY, finalDirectory);
        if (resource == null || (!resource.exists() && !resource.isReadable())) {
            resource = fileRepository.getDefaultImageResource();
        }
        return resource;
    }

    @Override
    public String save(MultipartFile file, String finalDirectory) throws IOException {
        /*
        The repository creates a random UUID of 37 characters which is added to the image name
        (and its extension) so that, rounding up, only a 40 character name can be placed as the
        database supports 80 characters.
        */
        if (file.getOriginalFilename().length() > 40) {
            throw new FileNameTooLongException("The file name is too long (max 40 characters)");
        }
        return fileRepository.saveImage(file, PRINCIPAL_DIRECTORY, finalDirectory);
    }

    @Override
    public boolean delete(String fileName, String finalDirectory) {
        return fileRepository.deleteImage(fileName, PRINCIPAL_DIRECTORY, finalDirectory);
    }
}
