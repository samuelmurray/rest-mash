package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContentFactory;
import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.wikipedia.WikipediaContentFactory;
import com.restmash.wikipedia.WikipediaContent;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

public class Artist {
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(String mbid, MusicBrainzContent musicBrainzContent) {
        this.mbid = mbid;
        this.musicBrainzContent = musicBrainzContent;
        wikipediaContent = createWikipediaContent();
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

    private WikipediaContent createWikipediaContent() {
        try {
            return createWikipediaContentSafe();
        } catch (NoSuchElementException | URISyntaxException e) {
            System.err.println(String.format("WikipediaContent not created due to Exception: %s", e));
            return null;
        }
    }

    private WikipediaContent createWikipediaContentSafe() throws URISyntaxException {
        try {
            return createWikipediaContentFromTitle();
        } catch (NoSuchElementException e) {
            return createWikipediaContentFromId();
        }
    }

    private WikipediaContent createWikipediaContentFromTitle() throws URISyntaxException {
        String title = musicBrainzContent.getWikipediaTitle();
        return WikipediaContentFactory.createFromWikipediaTitle(title);
    }

    private WikipediaContent createWikipediaContentFromId() throws URISyntaxException {
        String wikidataId = musicBrainzContent.getWikidataId();
        return WikipediaContentFactory.createFromWikidataId(wikidataId);
    }
}
