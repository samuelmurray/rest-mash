package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzContent {

    private String name;
    private Album[] albums;

    public MusicBrainzContent() {
    }

    public Album[] getAlbums() {
        return albums;
    }

    @JsonProperty(value = "release-groups")
    public void setAlbums(Album[] albums) {
        this.albums = albums;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCoverArtToAlbums() {
        for (Album album : albums) {
            album.addCoverArt();
        }
    }
}
