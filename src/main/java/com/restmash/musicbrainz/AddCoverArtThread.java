package com.restmash.musicbrainz;

import com.restmash.app.Album;

public class AddCoverArtThread extends Thread {
    private final Album album;

    public AddCoverArtThread(Album album) {
        this.album = album;
    }

    public void run() {
        album.addCoverArt();
    }
}
