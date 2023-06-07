package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.model.repositories.impl.FileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IFileRepositoryTest {

    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    private FileRepository fileRepository;

    private final String exampleFileName = "test-example.jpg";
    private final String defaultFileName = "no-image.jpg";
    private final String principalDirectory = "images";
    private final String finalDirectory = "/tests";
    private String fileCreatedForTest;

    @AfterEach
    void tearDown() {
        if (fileCreatedForTest != null) fileRepository.deleteImage(fileCreatedForTest, principalDirectory, finalDirectory);
    }

    @Test
    void getImageResource() {
        Resource resource = fileRepository.getImageResource(exampleFileName, principalDirectory, finalDirectory);
        assertNotNull(resource);
        assertEquals(resource.getFilename(), exampleFileName);
        assertTrue(resource.isReadable());
    }

    @Test
    void getImageResource_NotFound() {
        Resource resource = fileRepository.getImageResource("non-existing-file.jpg", principalDirectory, finalDirectory);
        assertFalse(resource.isReadable());
    }

    @Test
    void getDefaultImageResource() {
        Resource resource = fileRepository.getDefaultImageResource();
        assertNotNull(resource);
        assertEquals(resource.getFilename(), defaultFileName);
        assertTrue(resource.isReadable());
    }

    @Test
    void saveImage_success() throws IOException {
        //Creating a file to save
        InputStream inputStream = mock(InputStream.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test-example.jpg");
        when(multipartFile.getInputStream()).thenReturn(inputStream);

        String fileName = fileRepository.saveImage(multipartFile, principalDirectory, finalDirectory);
        Resource imageSaved = fileRepository.getImageResource(fileName, principalDirectory, finalDirectory);
        assertNotNull(imageSaved);
        assertTrue(imageSaved.isReadable());

        fileCreatedForTest = fileName; //to delete
    }

    @Test
    void saveImage_FileNameTooLong() {
        when(multipartFile.getOriginalFilename()).thenReturn("phraseOverFortyCharactersWhichCausesAnError.jpg");
        assertThrows(FileNameTooLongException.class, ()-> fileRepository.saveImage(multipartFile, principalDirectory, finalDirectory));
    }

    @Test
    void deleteImage() throws IOException {
        //Creating a file to delete
        InputStream inputStream = mock(InputStream.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test-example.jpg");
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        String fileName = fileRepository.saveImage(multipartFile, principalDirectory, finalDirectory);

        boolean isDeleted = fileRepository.deleteImage(fileName, principalDirectory, finalDirectory);
        assertTrue(isDeleted);
        assertFalse(fileRepository.getImageResource(fileName, principalDirectory, finalDirectory).isReadable());
    }
}