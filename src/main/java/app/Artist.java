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
    private final WikidataContent wikidataContent;
    private final WikipediaContent wikipediaContent;
    private final String enwikiTitle;

    public Artist(long id, String mbid) {
        this.id = id;
        this.mbid = mbid;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        musicBrainzContent = consumer.getContent();
        // musicBrainzContent.addCoverArtToAlbums();  // FIXME: Remove before merge
        String wikidataId = musicBrainzContent.getWikiDataId();
        WikidataConsumer wikiDataConsumer = new WikidataConsumer(wikidataId);
        wikidataContent = wikiDataConsumer.getContent();
        enwikiTitle = wikidataContent.getEnwikiTitle(wikidataId);
        WikipediaConsumer wikipediaConsumer = new WikipediaConsumer(enwikiTitle);
        wikipediaContent = wikipediaConsumer.getContent();
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
