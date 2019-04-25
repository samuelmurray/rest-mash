package com.restmash.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.net.URISyntaxException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzUrl {
    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String lastPartOfUrl() throws URISyntaxException {
        // FIXME: Error handling for if resource is not set
        URI uri = new URI(getResource());
        String[] segments = uri.getPath().split("/");
        return segments[segments.length - 1];
    }
}
