package com.agoda.downloader.bootstrap;

import lombok.Getter;

@Getter
public class AgodaResourceNotFoundException extends RuntimeException {

    private final String resource;

    public AgodaResourceNotFoundException(final String resource) {
        super("Agoda Resource could not be found : " + resource);
        this.resource = resource;
    }

}
