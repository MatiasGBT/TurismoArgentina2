package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.model.services.impl.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    private FileService fileService;

    private final String finalDirectory = "/tests";
    private String fileCreatedForTest;

    @AfterEach
    void tearDown() {
        if (fileCreatedForTest != null) fileService.delete(fileCreatedForTest, finalDirectory);
    }

    @Test
    void getPhoto_photoExist() {
        ResponseEntity<Resource> response = fileService.getPhoto("test-example.jpg", finalDirectory);
        assertNotNull(response);
        assertTrue(response.getBody().toString().contains("/images/tests/test-example.jpg"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getPhoto_photoDoesNotExist() {
        ResponseEntity<Resource> response = fileService.getPhoto("test-example.jpg", "/incorrect_directory");
        assertNotNull(response);
        assertTrue(response.getBody().toString().contains("/resources/static/images/no-image.jpg"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void save_success() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test-example.jpg");
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        String fileName = fileService.save(multipartFile, finalDirectory);
        assertNotNull(fileService.getPhoto(fileName, finalDirectory));
        fileCreatedForTest = fileName; //to delete
    }

    @Test
    void save_fileNameTooLong() {
        when(multipartFile.getOriginalFilename()).thenReturn("phraseOverFortyCharactersWhichCausesAnError.jpg");
        assertThrows(FileNameTooLongException.class, ()-> fileService.save(multipartFile, finalDirectory));
    }

    @Test
    void delete_success() throws IOException {
        //Creating a file to delete
        InputStream inputStream = mock(InputStream.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test-example.jpg");
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        String fileName = fileService.save(multipartFile, finalDirectory);

        boolean isDeleted = fileService.delete(fileName, finalDirectory);
        assertTrue(isDeleted);
        assertTrue(fileService.getPhoto(fileName, finalDirectory).toString().contains("no-image.jpg"));
    }

    @Test
    void delete_fileNameIsNull() {
        boolean isDeleted = fileService.delete("", finalDirectory);
        assertFalse(isDeleted);
    }

    @Test
    void getPath() {
        Path path = fileService.getPath("test-example.jpg", finalDirectory);
        assertNotNull(path);
        assertEquals(path.getFileName().toString(), "test-example.jpg");
    }
}