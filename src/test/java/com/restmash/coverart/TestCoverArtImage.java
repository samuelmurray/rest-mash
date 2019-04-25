package com.restmash.coverart;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class TestCoverArtImage {

    @Test
    void testGetUrlNull() {
        CoverArtImage image = new CoverArtImage();
        assertNull(image.getUrl());
    }

    @Test
    void testGetUrl() {
        CoverArtImage image = new CoverArtImage();
        String url = "https://www.google.com/";
        image.setUrl(url);
        assertEquals(url, image.getUrl());
    }
}
