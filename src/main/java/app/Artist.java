package app;

public class Artist {
    private final long id;
    private final MusicBrainzContent content;

    public Artist(long id, String mbid) {
        this.id = id;
        MusicBrainzConsumer consumer = new MusicBrainzConsumer(mbid);
        this.content = consumer.getContent();
    }

    public long getId() {
        return id;
    }

    public MusicBrainzContent getContent() {
        return content;
    }
}
