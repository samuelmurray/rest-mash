package com.restmash.app;

public class AddCoverArtRunnable implements Runnable {
    private final Album album;

    public AddCoverArtRunnable(Album album) {
        this.album = album;
    }

    public void run() {
        album.addCoverArt();
    }
}
