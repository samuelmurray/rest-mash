package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.wikipedia.WikipediaContent;
import com.restmash.wikipedia.WikipediaContentFactory;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

public class CreateWikipediaContentCallable implements Callable<WikipediaContent> {
    private MusicBrainzContent musicBrainzContent;

    public CreateWikipediaContentCallable(MusicBrainzContent musicBrainzContent) {
        this.musicBrainzContent = musicBrainzContent;
    }

    @Override
    public WikipediaContent call() {
        return createWikipediaContent();
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
