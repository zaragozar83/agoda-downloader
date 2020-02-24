package com.agoda.downloader.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
public class SaveTempImplTest {

    @InjectMocks
    private SaveTempImpl saveTemp;

    @Mock
    private Path pathTemporalFile;
    @Mock
    private InputStream inputStream;

    @BeforeEach
    void setup() throws IOException {
        pathTemporalFile = Files.createTempFile("agoda","_" + "java8book-2172125.pdf");
    }

    @Test
    void saveTemporalFileTest() throws IOException {

        Mockito.verify(saveTemp).saveTemporalFile(pathTemporalFile,inputStream);
    }

}
