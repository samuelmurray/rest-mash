package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;


public class AddCoverArtToAlbumsRunnable implements Runnable {
    private final MusicBrainzContent content;

    public AddCoverArtToAlbumsRunnable(MusicBrainzContent content) {
        this.content = content;
    }

    public void run() {
        content.addCoverArtToAlbums();
    }
}
