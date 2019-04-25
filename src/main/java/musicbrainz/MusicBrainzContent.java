package musicbrainz;

import app.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzContent {
    private String name;
    private Album[] albums;
    private MusicBrainzRelation[] relations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album[] getAlbums() {
        return albums;
    }

    @JsonProperty(value = "release-groups")
    public void setAlbums(Album[] albums) {
        this.albums = albums;
    }

    public MusicBrainzRelation[] getRelations() {
        return relations;
    }

    public void setRelations(MusicBrainzRelation[] relations) {
        this.relations = relations;
    }

    public void addCoverArtToAlbums() {
        for (Album album : albums) {
            album.addCoverArt();
        }
    }

    public String getWikidataId() throws URISyntaxException {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals("wikidata")) {
                return relation.getLastPartOfUrl();
            }
        }
        throw new NoSuchElementException("Wikidata not found");
    }

    public String getWikipediaTitle() throws URISyntaxException {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals("wikipedia")) {
                return relation.getLastPartOfUrl();
            }
        }
        throw new NoSuchElementException("Wikipedia relation not found");
    }
}
