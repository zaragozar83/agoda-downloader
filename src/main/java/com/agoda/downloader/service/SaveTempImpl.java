package com.agoda.downloader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;

@Slf4j
@Service
public class SaveTempImpl implements SaveTemp {

    @Override
    public void saveTemporalFile(Path pathTemporalFile, InputStream inputStream) throws IOException {


        OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(pathTemporalFile.toFile()));
        try {
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }

        } finally {
            outputStream2.close();
            inputStream.close();
        }
    }
}
