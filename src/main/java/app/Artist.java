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
        wikipediaContent = createWikipediaContent();
    }

    private WikipediaContent createWikipediaContent() {
        String title = createWikipediaTitle();
        WikipediaConsumer wikipediaConsumer = new WikipediaConsumer(title);
        return wikipediaConsumer.getContent();
    }

    private String createWikipediaTitle() {
        String wikidataId = musicBrainzContent.getWikiDataId();
        WikidataConsumer consumer = new WikidataConsumer(wikidataId);
        WikidataContent content = consumer.getContent();
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
        return wikipediaContent.getExtract();
    }
}
