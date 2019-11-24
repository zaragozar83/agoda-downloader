package com.agoda.downloader.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface SaveTemp {

    void saveTemporalFile(Path pathTemporalFile, InputStream inputStream) throws IOException;
}
