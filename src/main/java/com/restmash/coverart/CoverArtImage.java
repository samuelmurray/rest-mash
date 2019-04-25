package com.restmash.coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArtImage {
    private String url;

    public String getUrl() {
        return url;
    }

    @JsonProperty(value = "image", defaultValue = "MISSING")
    public void setUrl(String url) {
        this.url = url;
    }
}
