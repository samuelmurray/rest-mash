package com.restmash.app;

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
    void getImagesNull() {
        Album album = new Album();
        assertNull(album.getImages());
    }

    @Test
    void addCoverArt() {
        String mbid = "438bcaf5-644b-3036-bf0d-bcc96f6482cf";
        Album album = new Album();
        album.setMbid(mbid);
        album.addCoverArt();
        assertNotNull(album.getImages());
    }

    @Test
    void addCoverArtMissing() {
        String mbid = "4de7f50b-0a20-47bd-b29b-67568d1a866d";
        Album album = new Album();
        album.setMbid(mbid);
        album.addCoverArt();
        assertNull(album.getImages());
    }

    @Test
    void addCoverArtNull() {
        Album album = new Album();
        album.addCoverArt();
        assertNull(album.getImages());
    }
}
