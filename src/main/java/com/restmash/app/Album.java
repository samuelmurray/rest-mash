package com.restmash.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restmash.coverart.CoverArtContentFactory;
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

    public void addCoverArt() {
        content = CoverArtContentFactory.createFromMbid(mbid);
    }

    public CoverArtImage[] getImages() {
        if (content == null) {
            return null;
        }
        return content.getImages();
    }
}
