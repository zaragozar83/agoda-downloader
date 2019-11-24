package com.agoda.downloader.service;

import com.agoda.downloader.domain.Protocol;

import java.io.InputStream;
import java.util.List;

public interface ProcessFile {

    InputStream download(String uri) throws Exception;
    List<Protocol> getSupportedProtocols();
}
