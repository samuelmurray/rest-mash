package com.restmash.app;

import com.restmash.coverart.CoverArtContent;
import com.restmash.coverart.CoverArtContentFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class TestAlbum {

    @Test
    void testGetTitle() {
        Album album = new Album();
        String expected = "test";
        album.setTitle(expected);
        assertEquals(expected, album.getTitle());
    }

    @Test
    void testGetTitleNull() {
        Album album = new Album();
        assertNull(album.getTitle());
    }

    @Test
    void testGetMbid() {
        Album album = new Album();
        String expected = "test";
        album.setMbid(expected);
        assertEquals(expected, album.getMbid());
    }

    @Test
    void testGetMbidNull() {
        Album album = new Album();
        assertNull(album.getMbid());
    }

    @Test
    void testGetImagesNull() {
        Album album = new Album();
        assertNull(album.getImages());
    }

    @Test
    void testSetImages() {
        String mbid = "438bcaf5-644b-3036-bf0d-bcc96f6482cf";
        Album album = new Album();
        album.setMbid(mbid);
        CoverArtContent content = CoverArtContentFactory.createFromMbid(mbid);
        album.setContent(content);
        assertNotNull(album.getImages());
    }

    @Test
    void testSetContentMissingImages() {
        String mbid = "4de7f50b-0a20-47bd-b29b-67568d1a866d";
        Album album = new Album();
        album.setMbid(mbid);
        CoverArtContent content = CoverArtContentFactory.createFromMbid(mbid);
        album.setContent(content);
        assertNull(album.getImages());
    }
}
