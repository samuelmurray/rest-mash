package app;

import musicbrainz.MusicBrainzConsumer;
import musicbrainz.MusicBrainzContent;
import wikidata.WikidataConsumer;
import wikidata.WikidataContent;
import wikipedia.WikipediaConsumer;
import wikipedia.WikipediaContent;

public class Artist {
    private final long id;
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(long id, String mbid) {
        this.id = id;
        this.mbid = mbid;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        musicBrainzContent = consumer.getContent();
        // musicBrainzContent.addCoverArtToAlbums();  // FIXME: Remove before merge
        String wikidataId = musicBrainzContent.getWikiDataId();
        wikipediaContent = createWikipediaContent(wikidataId);
    }

    private WikipediaContent createWikipediaContent(String wikidataId) {
        WikidataConsumer wikiDataConsumer = new WikidataConsumer(wikidataId);
        WikidataContent wikidataContent = wikiDataConsumer.getContent();
        String enwikiTitle = wikidataContent.getEnwikiTitle(wikidataId);
        WikipediaConsumer wikipediaConsumer = new WikipediaConsumer(enwikiTitle);
        return wikipediaConsumer.getContent();
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
