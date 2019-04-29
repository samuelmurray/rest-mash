package com.restmash.musicbrainz;

public class AddCoverArtRunnable implements Runnable {
    private final MusicBrainzContent content;

    public AddCoverArtRunnable(MusicBrainzContent content) {
        this.content = content;
    }

    public void run() {
        content.addCoverArtToAlbums();
    }
}
