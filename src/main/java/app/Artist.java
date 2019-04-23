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
    private final MusicBrainzContent content;
    private final WikidataContent wikidataContent;
    private final WikipediaContent wikipediaContent;
    private final String enwikiTitle;

    public Artist(long id, String mbid) {
        this.id = id;
        this.mbid = mbid;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        content.addCoverArtToAlbums();
        String wikidataId = content.getWikiDataId();
        WikidataConsumer wikiDataConsumer = new WikidataConsumer(wikidataId);
        wikidataContent = wikiDataConsumer.getContent();
        enwikiTitle = wikidataContent.getEnwikiTitle(wikidataId);
        WikipediaConsumer wikipediaConsumer = new WikipediaConsumer(enwikiTitle);
        wikipediaContent = wikipediaConsumer.getContent();
    }

    public String getMbid() {
        return mbid;
    }

    public long getId() {
        return id;
    }

    public WikidataContent getWikidataContent() {
        return wikidataContent;
    }

    public String getEnwikiTitle() {
        return enwikiTitle;
    }

    public WikipediaContent getWikipediaContent() {
        return wikipediaContent;
    }

    public MusicBrainzContent getContent() {
        return content;
    }
}
