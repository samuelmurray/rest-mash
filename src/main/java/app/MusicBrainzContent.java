package app;

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

    public String getWikiUrl() {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals("wikidata")) {
                return getUrlFromRelation(relation);
            }
        }
        return "MISSING";
    }

    private String getUrlFromRelation(MusicBrainzRelation relation) {
        try {
            String id = relation.getUrl().lastPartOfUrl();
            return buildWikiUrl(id);
        } catch (URISyntaxException e) {
            return "MISSING";
        }
    }

    private String buildWikiUrl(String id) {
        return "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + id + "&format=json&props=sitelinks";
    }
}
