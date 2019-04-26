package com.restmash.app;

import com.restmash.coverart.CoverArtContent;
import com.restmash.coverart.CoverArtContentFactory;

public class AddCoverArtRunnable implements Runnable {
    private final Album album;

    public AddCoverArtRunnable(Album album) {
        this.album = album;
    }

    public void run() {
        String mbid = album.getMbid();
        CoverArtContent content = CoverArtContentFactory.createFromMbid(mbid);
        album.setContent(content);
    }
}
