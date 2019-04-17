package app;

public class Album {
    private String title;
    private String id;
    private CoverArtContent content;

    public Album() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addCoverArt() {
        CoverArtConsumer consumer = new CoverArtConsumer(id);
        content = consumer.getContent();
    }

    public CoverArtContent getContent() {
        return content;
    }
}
