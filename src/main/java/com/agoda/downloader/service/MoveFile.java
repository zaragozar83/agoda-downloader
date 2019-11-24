package com.agoda.downloader.service;

import java.io.IOException;

public interface MoveFile {

    void moveFileToLocal(String path, String fileName) throws IOException;
}
