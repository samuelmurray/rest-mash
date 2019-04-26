package com.restmash.musicbrainz;

import com.restmash.app.Album;

public class AddCoverArtRunnable implements Runnable {
    private final Album album;

    public AddCoverArtRunnable(Album album) {
        this.album = album;
    }

    public void run() {
        album.addCoverArt();
        System.err.println("REMOVE ME");
    }
}
