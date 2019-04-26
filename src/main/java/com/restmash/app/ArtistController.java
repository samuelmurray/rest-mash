package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.musicbrainz.MusicBrainzContentFactory;
import com.restmash.wikipedia.WikipediaContent;
import com.restmash.wikipedia.WikipediaContentFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@RestController
public class ArtistController {
    private MusicBrainzContent musicBrainzContent;

    @RequestMapping("/artist")
    public Artist artist(@RequestParam(value = "mbid") String mbid) {
        musicBrainzContent = MusicBrainzContentFactory.createFromMbid(mbid);
        musicBrainzContent.addCoverArtToAlbums();
        WikipediaContent wikipediaContent = createWikipediaContent();
        return new Artist(mbid, musicBrainzContent, wikipediaContent);
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
