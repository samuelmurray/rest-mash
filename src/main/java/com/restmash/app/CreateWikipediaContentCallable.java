package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.wikipedia.WikipediaContent;
import com.restmash.wikipedia.WikipediaContentFactory;

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
            return createWikipediaContentFromTitleOrId();
        } catch (NoSuchElementException e) {
            System.err.println(String.format("WikipediaContent not created due to Exception: %s", e));
            return null;
        }
    }

    private WikipediaContent createWikipediaContentFromTitleOrId() {
        try {
            return createWikipediaContentFromTitle();
        } catch (NoSuchElementException e) {
            return createWikipediaContentFromId();
        }
    }

    private WikipediaContent createWikipediaContentFromTitle() {
        String title = musicBrainzContent.getWikipediaTitle();
        return WikipediaContentFactory.createFromWikipediaTitle(title);
    }

    private WikipediaContent createWikipediaContentFromId() {
        String wikidataId = musicBrainzContent.getWikidataId();
        return WikipediaContentFactory.createFromWikidataId(wikidataId);
    }
}
