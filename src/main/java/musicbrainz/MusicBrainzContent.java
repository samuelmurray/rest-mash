package musicbrainz;

import app.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzContent {

    private String name;
    private Album[] albums;
    private MusicBrainzRelation[] relations;

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

    public String getWikidataId() {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals("wikidata")) {
                return getWikidataIdFromRelation(relation);
            }
        }
        return "MISSING";
    }

    private String getWikidataIdFromRelation(MusicBrainzRelation relation) {
        try {
            return relation.getUrl().lastPartOfUrl();
        } catch (URISyntaxException e) {
            return "MISSING";
        }
    }
}
