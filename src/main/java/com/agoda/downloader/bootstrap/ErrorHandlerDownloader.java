package com.agoda.downloader.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ControllerAdvice
public class ErrorHandlerDownloader {

    private static String NOT_RESOURCE_FOUND = "Not Agoda Resource Found";

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public void handleError(HttpClientErrorException e, HttpServletResponse response) throws IOException {

        response.sendError(e.getRawStatusCode(), e.getStatusText());
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public void handleNotFound(final HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, NOT_RESOURCE_FOUND);
    }

}
