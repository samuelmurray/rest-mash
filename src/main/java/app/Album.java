package app;

import com.fasterxml.jackson.annotation.JsonProperty;
import coverart.CoverArtConsumer;
import coverart.CoverArtContent;

public class Album {
    private String title;
    private String mbid;
    private CoverArtContent content;

    public Album() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty(value = "id")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getMbid() {
        return mbid;
    }

    public void addCoverArt() {
        CoverArtConsumer consumer = new CoverArtConsumer(mbid);
        content = consumer.getContent();
    }

    public CoverArtContent getContent() {
        return content;
    }
}
