package com.restmash.musicbrainz;

import com.restmash.app.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

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
        int parallelism = 20;
        ForkJoinPool myPool = new ForkJoinPool(parallelism);
        List<Album> albumList = Arrays.asList(albums);
        try {
            myPool.submit(() ->
                    albumList.parallelStream().forEach(Album::addCoverArt)
                    ).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getWikidataId() throws URISyntaxException {
        String type = "wikidata";
        return getLastPartOfUrlForType(type);
    }

    public String getWikipediaTitle() throws URISyntaxException {
        String type = "wikipedia";
        return getLastPartOfUrlForType(type);
    }

    private String getLastPartOfUrlForType(String type) throws URISyntaxException {
        for (MusicBrainzRelation relation : relations) {
            if (relation.getType().equals(type)) {
                return relation.getLastPartOfUrl();
            }
        }
        throw new NoSuchElementException(String.format("Relation \"%s\" not found", type));
    }
}
