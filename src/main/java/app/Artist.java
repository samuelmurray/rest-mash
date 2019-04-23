package app;

import musicbrainz.MusicBrainzContentFactory;
import musicbrainz.MusicBrainzContent;
import wikidata.WikidataContentFactory;
import wikidata.WikidataContent;
import wikipedia.WikipediaContentFactory;
import wikipedia.WikipediaContent;

import java.net.URISyntaxException;

public class Artist {
    private final long id;
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(long id, String mbid) {
        this.id = id;
        this.mbid = mbid;
        musicBrainzContent = createMusicBrainzContent(mbid);
        musicBrainzContent.addCoverArtToAlbums();
        wikipediaContent = createWikipediaContent();
    }

    private MusicBrainzContent createMusicBrainzContent(String mbid) {
        return MusicBrainzContentFactory.createFromMbid(mbid);
    }

    private WikipediaContent createWikipediaContent() {
        try {
            String title = createWikipediaTitle();
            return WikipediaContentFactory.createFromWikipediaTitle(title);
        } catch (RuntimeException e) {
            System.err.println(String.format("WikipediaContent not created due to RuntimeException: %s", e));
            return null;
        } catch (URISyntaxException e) {
            System.err.println(String.format("WikipediaContent not created due to URISyntaxException: %s", e));
            return null;
        }
    }

    private String createWikipediaTitle() throws URISyntaxException {
        String wikidataId = musicBrainzContent.getWikidataId();
        System.out.println(wikidataId);
        WikidataContent content = WikidataContentFactory.createFromWikidataId(wikidataId);
        return content.getEnwikiTitle(wikidataId);
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
