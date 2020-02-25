package com.agoda.downloader.service;

import com.agoda.downloader.domain.Protocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProcessFileHttpTest {

    @InjectMocks
    private ProcessFileHttp processFileHttp;

    @Mock
    private RestTemplate appRestClient;


    private final String sampleToInputStream = "Test";
    private final String URI = "uri";

    @Mock
    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        inputStream = new ByteArrayInputStream(sampleToInputStream.getBytes());
    }

    @Test
    public void downloadTest() throws Exception {

        when(appRestClient.execute(anyString(),
                                   eq(HttpMethod.GET),
                                   isNull(),
                                   any()))
                          .thenReturn(inputStream);

        InputStream streamResult = processFileHttp.download(URI);

        assertEquals(inputStream, streamResult);

    }

    @Test
    public void getSupportedProtocolsTest() {

        List<Protocol> protocols = Arrays.asList(Protocol.HTTP, Protocol.HTTPS);
        assertTrue(protocols.containsAll(processFileHttp.getSupportedProtocols()));

    }

    @Test
    @DisplayName("Not Found Exception Agoda Resource")
    void downloadResourceNotFoundException() {

        Mockito.when(appRestClient.execute(anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any()))
                .thenThrow(HttpClientErrorException.NotFound.class);


        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> processFileHttp.download(URI));

    }

}
