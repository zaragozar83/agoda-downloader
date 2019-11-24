package com.agoda.downloader.service;

import com.agoda.downloader.domain.Protocol;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProcessFileHttp implements ProcessFile {

    private final RestTemplate appRestClient;

    @Override

    public InputStream download(String uri) throws Exception {

        log.debug("HTTP file to download, {}", uri.toString());
        return appRestClient.execute(uri, HttpMethod.GET, null, HttpInputMessage::getBody);
    }

    @Override
    public List<Protocol> getSupportedProtocols() {
        return Arrays.asList(Protocol.HTTP, Protocol.HTTPS);

    }
}
