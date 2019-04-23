package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;
    private final WikidataContent wikidataContent;
    private final String enwikiTitle;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        content.addCoverArtToAlbums();
        String wikidataId = content.getWikiDataId();
        WikidataConsumer wikiDataConsumer = new WikidataConsumer(wikidataId);
        wikidataContent = wikiDataConsumer.getContent();
        enwikiTitle = wikidataContent.getEnwikiTitle(wikidataId);
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

    public MusicBrainzContent getContent() {
        return content;
    }
}
