package com.restmash.album;

import com.restmash.coverart.CoverArtContent;
import com.restmash.coverart.CoverArtContentFactory;

public class AddCoverArtToAlbumRunnable implements Runnable {
    private final Album album;

    public AddCoverArtToAlbumRunnable(Album album) {
        this.album = album;
    }

    public void run() {
        String mbid = album.getMbid();
        CoverArtContent content = CoverArtContentFactory.createFromMbid(mbid);
        album.setContent(content);
    }
}
