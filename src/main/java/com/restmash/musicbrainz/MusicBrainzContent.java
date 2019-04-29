package com.restmash.musicbrainz;

import com.restmash.album.AddCoverArtToAlbumRunnable;
import com.restmash.album.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        ExecutorService service = Executors.newCachedThreadPool();
        for (Album album : albums) {
            service.execute(new AddCoverArtToAlbumRunnable(album));
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getWikidataId() {
        String type = "wikidata";
        return getLastPartOfUrlForType(type);
    }

    public String getWikipediaTitle() {
        String type = "wikipedia";
        return getLastPartOfUrlForType(type);
    }

    private String getLastPartOfUrlForType(String type) {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals(type)) {
                try {
                    return relation.getLastPartOfUrl();
                } catch (URISyntaxException e) {
                    throw new NoSuchElementException(String.format("Relation \"%s\" not found", type));
                }
            }
        }
        throw new NoSuchElementException(String.format("Relation \"%s\" not found", type));
    }
}
