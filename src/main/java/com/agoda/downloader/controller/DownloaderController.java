package com.agoda.downloader.controller;

import com.agoda.downloader.service.DownloaderProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/downloader")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DownloaderController {

    private final DownloaderProcess downloaderProcess;


    @PostMapping
    public CompletableFuture<Void> processFiles(@RequestBody @Valid List<String> files) {

        return downloaderProcess.download(files);
    }
}
