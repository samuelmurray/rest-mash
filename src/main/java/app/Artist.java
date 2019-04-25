package app;

import musicbrainz.MusicBrainzContentFactory;
import musicbrainz.MusicBrainzContent;
import wikidata.WikidataContentFactory;
import wikidata.WikidataContent;
import wikipedia.WikipediaContentFactory;
import wikipedia.WikipediaContent;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

public class Artist {
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(String mbid) {
        this.mbid = mbid;
        musicBrainzContent = createMusicBrainzContent(mbid);
        musicBrainzContent.addCoverArtToAlbums();
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

    private MusicBrainzContent createMusicBrainzContent(String mbid) {
        return MusicBrainzContentFactory.createFromMbid(mbid);
    }

    private WikipediaContent createWikipediaContent() {
        try {
            String title = createWikipediaTitle();
            return WikipediaContentFactory.createFromWikipediaTitle(title);
        } catch (NoSuchElementException e) {
            System.err.println(String.format("WikipediaContent not created due to NoSuchElementException: %s", e));
            return null;
        } catch (URISyntaxException e) {
            System.err.println(String.format("WikipediaContent not created due to URISyntaxException: %s", e));
            return null;
        }
    }

    private String createWikipediaTitle() throws URISyntaxException {
        try {
            return musicBrainzContent.getWikipediaTitle();
        } catch (NoSuchElementException e) {
            return createWikipediaTitleFromWikidata();
        }
    }

    private String createWikipediaTitleFromWikidata() throws URISyntaxException {
        String wikidataId = musicBrainzContent.getWikidataId();
        WikidataContent content = WikidataContentFactory.createFromWikidataId(wikidataId);
        return content.getEnwikiTitle(wikidataId);
    }
}
