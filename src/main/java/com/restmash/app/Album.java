package com.restmash.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restmash.coverart.CoverArtContent;
import com.restmash.coverart.CoverArtImage;

public class Album {
    private String title;
    private String mbid;
    private CoverArtContent content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMbid() {
        return mbid;
    }

    @JsonProperty(value = "id")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public void setContent(CoverArtContent content) {
        this.content = content;
    }

    public CoverArtImage[] getImages() {
        if (content == null) {
            return null;
        }
        return content.getImages();
    }
}
