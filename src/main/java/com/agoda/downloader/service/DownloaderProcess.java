package com.agoda.downloader.service;

import com.agoda.downloader.domain.Protocol;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DownloaderProcess {

    private final List<ProcessFile> processors;
    private final MoveFile moveFile;
    private final SaveTemp saveTemp;

    public CompletableFuture<Void> download(List<String> files){

        CompletableFuture[] promises = files.stream()
                .map(this::download)
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(promises);

    }

    public CompletableFuture<Void> download(String file) {

        return CompletableFuture.runAsync(() -> {
            try{
                URI uri = new URI(file);

                String fileName = Paths.get(uri.getPath()).getFileName().toString();
                Path pathTemporalFile = Files.createTempFile("agoda", "_" + fileName);
                Protocol protocol = Protocol.valueOf(uri.getScheme().toUpperCase());
                ProcessFile processFile = processors.stream()
                        .filter(processor -> processor.getSupportedProtocols().contains(protocol))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("Unsupported protocol"));

                InputStream inputStream = processFile.download(file);

                saveTemp.saveTemporalFile(pathTemporalFile, inputStream);

                moveFile.moveFileToLocal(pathTemporalFile.toFile().getPath(), fileName);


            } catch (Exception e) {
                log.error("Error processing file", e);
                throw new RuntimeException(e);
            }
        });


    }
}
