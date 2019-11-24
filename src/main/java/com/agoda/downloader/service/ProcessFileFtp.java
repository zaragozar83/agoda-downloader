package com.agoda.downloader.service;

import com.agoda.downloader.domain.Protocol;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProcessFileFtp implements ProcessFile {

    @Override
    public InputStream download(String file) {

        URI uri = null;
        try {
            log.debug("FTP file to download, {}", file.toString());
            uri = new URI(file);

            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(uri.getHost());
            ftpClient.login("anonymous", "");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFileStream(uri.getPath());

        }  catch (Exception e) {
            log.error("Error downloading file", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Protocol> getSupportedProtocols() {
        return Arrays.asList(Protocol.FTP, Protocol.SFTP);
    }
}
