package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;
    private final WikiDataContent wikiDataContent;
    private final String enwikiTitle;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        content.addCoverArtToAlbums();
        String wikiDataId = content.getWikiDataId();
        WikiDataConsumer wikiDataConsumer = new WikiDataConsumer(wikiDataId);
        wikiDataContent = wikiDataConsumer.getContent();
        enwikiTitle = wikiDataContent.getEnwikiTitle(wikiDataId);
    }

    public long getId() {
        return id;
    }

    public WikiDataContent getWikiDataContent() {
        return wikiDataContent;
    }

    public String getEnwikiTitle() {
        return enwikiTitle;
    }

    public MusicBrainzContent getContent() {
        return content;
    }
}
