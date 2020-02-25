package com.agoda.downloader.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MoveFileImpl implements MoveFile{

    @Value("${path.root}")
    private String pathRoot;

    @Override
    public void moveFileToLocal(String path, String fileName) throws IOException {

        log.debug("Moving file from {}", path);

        Files.move(Paths.get(path), fileWithDirectoryAssurance(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    private Path fileWithDirectoryAssurance(String filename) {

        String directory = System.getProperty("user.dir").concat(pathRoot);

        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + "/" + filename).toPath();
    }
}
