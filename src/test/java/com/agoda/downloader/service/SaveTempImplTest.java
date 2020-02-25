package com.agoda.downloader.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
public class SaveTempImplTest {

    @Mock
    private Path pathTemporalFile;

    @Mock
    private InputStream inputStream;

    private final String sampleToInputStream = "Test";

    @BeforeEach
    void setup() throws IOException {
        pathTemporalFile = Files.createTempFile("agoda","_" + "java8book-2172125.pdf");
        inputStream = new ByteArrayInputStream(sampleToInputStream.getBytes());
    }

    @Test
    void saveTemporalFileTest() throws IOException {

        SaveTempImpl saveTemp = Mockito.spy(SaveTempImpl.class);

        Mockito.doNothing().when(saveTemp).saveTemporalFile(pathTemporalFile, inputStream);
    }

}
