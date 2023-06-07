package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.model.repositories.IFileRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private IFileRepository fileRepository;

    @InjectMocks
    private FileService fileService;

    private final String exampleFileName = "test-example.jpg";
    private final String principalDirectory = "images";
    private final String finalDirectory = "/tests";

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPhoto() {
        Resource mockResource = mock(Resource.class);
        when(fileRepository.getImageResource(exampleFileName, principalDirectory, finalDirectory)).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(true);
        Resource resource = fileService.getPhoto(exampleFileName, finalDirectory);
        assertNotNull(resource);
        assertSame(resource, mockResource);
        verify(fileRepository, times(1)).getImageResource(exampleFileName, principalDirectory, finalDirectory);
        verify(mockResource, times(1)).exists();
    }

    @Test
    void getPhoto_NotFound() {
        Resource mockResource = mock(Resource.class);
        when(fileRepository.getImageResource(exampleFileName, principalDirectory, finalDirectory)).thenReturn(mockResource);
        when(fileRepository.getDefaultImageResource()).thenReturn(mockResource);
        when(mockResource.exists()).thenReturn(false);
        when(mockResource.isReadable()).thenReturn(false);
        Resource resource = fileService.getPhoto(exampleFileName, finalDirectory);
        assertNotNull(resource);
        assertSame(resource, mockResource);
        verify(fileRepository, times(1)).getImageResource(exampleFileName, principalDirectory, finalDirectory);
        verify(fileRepository, times(1)).getDefaultImageResource();
        verify(mockResource, times(1)).exists();
        verify(mockResource, times(1)).isReadable();
    }

    @Test
    void save() throws IOException {
        MultipartFile mockFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[0]);
        when(fileRepository.saveImage(mockFile, principalDirectory, finalDirectory)).thenReturn("example-file.jpg");
        String fileName = fileService.save(mockFile, finalDirectory);
        assertEquals(fileName, "example-file.jpg");
        verify(fileRepository, times(1)).saveImage(mockFile, principalDirectory, finalDirectory);
    }

    @Test
    void save_FileNameTooLong() throws IOException {
        MultipartFile mockFile = new MockMultipartFile("file", "phraseOverFortyCharactersWhichCausesAnError.jpg", "image/jpeg", new byte[0]);
        when(fileRepository.saveImage(mockFile, principalDirectory, finalDirectory)).thenThrow(FileNameTooLongException.class);
        assertThrows(FileNameTooLongException.class, () -> fileService.save(mockFile, finalDirectory));
        verify(fileRepository, times(1)).saveImage(mockFile, principalDirectory, finalDirectory);
    }

    @Test
    void delete() {
        when(fileRepository.deleteImage(exampleFileName, principalDirectory, finalDirectory)).thenReturn(true);
        boolean isDeleted = fileService.delete(exampleFileName, finalDirectory);
        assertTrue(isDeleted);
        verify(fileRepository, times(1)).deleteImage(exampleFileName, principalDirectory, finalDirectory);
    }
}