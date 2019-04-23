package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;
    private final String wikiUrl;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        // content.addCoverArtToAlbums();  // FIXME: Remove before merging
        wikiUrl = content.getWikiUrl();
    }

    public long getId() {
        return id;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public MusicBrainzContent getContent() {
        return content;
    }
}
