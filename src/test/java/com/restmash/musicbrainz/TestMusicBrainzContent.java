package com.restmash.musicbrainz;

import com.restmash.album.Album;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

class TestMusicBrainzContent {

    @Test
    void testContentFactoryName() {
        String mbid = "5b11f4ce-a62d-471e-81fc-a69a8278c7da";
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        String expected = "Nirvana";
        assertEquals(expected, content.getName());
    }

    @Test
    void testContentFactoryWikipediaTitleMissing() {
        String mbid = "5b11f4ce-a62d-471e-81fc-a69a8278c7da";
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        Assertions.assertThrows(NoSuchElementException.class, content::getWikipediaTitle);
    }

    @Test
    void testContentFactoryWikipediaTitle() {
        String mbid = "a04e7da2-998b-4e36-abaa-014c00b93622";
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        String expected = "Veronica_Maggio";
        assertEquals(expected, content.getWikipediaTitle());
    }

    @Test
    void testContentFactoryWikidataId() {
        String mbid = "a04e7da2-998b-4e36-abaa-014c00b93622";
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        String expected = "Q260597";
        assertEquals(expected, content.getWikidataId());
    }

    @Test
    void testContentFactoryWikidataIdMissing() {
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        Assertions.assertThrows(NoSuchElementException.class, content::getWikidataId);
    }

    @Test
    void testGetAlbums() {
        MusicBrainzContent content = new MusicBrainzContent();
        Album album = new Album();
        Album[] albums = new Album[]{album};
        content.setAlbums(albums);
        assertArrayEquals(albums, content.getAlbums());
    }

    @Test
    void testGetAlbumsNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getAlbums());
    }

    @Test
    void testGetName() {
        MusicBrainzContent content = new MusicBrainzContent();
        String name = "name";
        content.setName(name);
        assertEquals(name, content.getName());
    }

    @Test
    void testGetNameNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getName());
    }

    @Test
    void testGetRelations() {
        MusicBrainzContent content = new MusicBrainzContent();
        MusicBrainzRelation relation = new MusicBrainzRelation();
        MusicBrainzRelation[] relations = new MusicBrainzRelation[]{relation};
        content.setRelations(relations);
        assertArrayEquals(relations, content.getRelations());
    }

    @Test
    void testGetRelationsNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getRelations());
    }
}
