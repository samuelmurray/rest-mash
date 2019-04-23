package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        content = consumer.getContent();
        content.addCoverArtToAlbums();
    }

    public long getId() {
        return id;
    }

    public MusicBrainzContent getContent() {
        return content;
    }
}
