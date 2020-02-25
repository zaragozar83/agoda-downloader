package com.agoda.downloader.controller;

import com.agoda.downloader.service.DownloaderProcess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class DownloaderControllerTest {

    @InjectMocks
    private DownloaderController controller;

    @Mock
    private DownloaderProcess downloaderProcess;

    private List<String> urls;

    @BeforeEach
    public void setUp() {
        urls = Arrays.asList("URL_FTP", "URL_HTTP");
    }

    @Test
    @DisplayName("Process controller to download files from uri's")
    void downloadFile() {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("complete"));
        Mockito.when(downloaderProcess.download(urls)).thenReturn(completableFuture);

        CompletableFuture<Void> voidCompletableFuture = controller.processFiles(urls);

        Assertions.assertEquals(completableFuture, voidCompletableFuture);

    }

}
