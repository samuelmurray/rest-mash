package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;
    private final WikidataContent wikidataContent;
    private final WikipediaContent wikipediaContent;
    private final String enwikiTitle;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        // content.addCoverArtToAlbums();  // FIXME: Add before merge
        String wikidataId = content.getWikiDataId();
        WikidataConsumer wikiDataConsumer = new WikidataConsumer(wikidataId);
        wikidataContent = wikiDataConsumer.getContent();
        enwikiTitle = wikidataContent.getEnwikiTitle(wikidataId);
        WikipediaConsumer wikipediaConsumer = new WikipediaConsumer(enwikiTitle);
        wikipediaContent = wikipediaConsumer.getContent();
        System.out.println(wikipediaContent.getExtract());
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
