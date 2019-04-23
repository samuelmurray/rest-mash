package app;

import musicbrainz.MusicBrainzContentFactory;
import musicbrainz.MusicBrainzContent;
import wikidata.WikidataContentFactory;
import wikidata.WikidataContent;
import wikipedia.WikipediaContentFactory;
import wikipedia.WikipediaContent;

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
        String title = createWikipediaTitle();
        WikipediaContentFactory wikipediaContentFactory = new WikipediaContentFactory(title);
        return wikipediaContentFactory.getContent();
    }

    private String createWikipediaTitle() {
        String wikidataId = musicBrainzContent.getWikidataId();
        WikidataContent content = createWikidataContent(wikidataId);
        return content.getEnwikiTitle(wikidataId);
    }

    private WikidataContent createWikidataContent(String wikidataId) {
        return WikidataContentFactory.createFromWikidataId(wikidataId);
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
        return wikipediaContent.getExtract();
    }
}
