package com.restmash.app;

import com.restmash.album.Album;
import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.wikipedia.WikipediaContent;

public class Artist {
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(String mbid, MusicBrainzContent musicBrainzContent, WikipediaContent wikipediaContent) {
        this.mbid = mbid;
        this.musicBrainzContent = musicBrainzContent;
        this.wikipediaContent = wikipediaContent;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return musicBrainzContent.getName();
    }

    public Album[] getAlbums() {
        return musicBrainzContent.getAlbums();
    }

    public String getDescription() {
        if (wikipediaContent == null) {
            return "";
        }
        return wikipediaContent.getExtract();
    }
}
