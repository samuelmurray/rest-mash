package musicbrainz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TestMusicBrainzUrl {

    @Test
    void testGetResourceNull() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        assertNull(url.getResource());
    }

    @Test
    void testGetResource() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        String resource = "test";
        url.setResource(resource);
        assertEquals(resource, url.getResource());
    }

    @Test
    void testLastPartOfUrl() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        String resource = "https://www.google.com/webhp";
        url.setResource(resource);
        String lastPart = "webhp";
        try {
            assertEquals(lastPart, url.lastPartOfUrl());
        } catch (URISyntaxException e) {
            fail("URISyntaxException thrown");
        }
    }

    @Test
    void testLastPartOfUrlException() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        String resource = "www s 5com";
        url.setResource(resource);
        Assertions.assertThrows(URISyntaxException.class, url::lastPartOfUrl);
    }

    @Test
    void testLastPartOfUrlNullResource() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        Assertions.assertThrows(NullPointerException.class, url::lastPartOfUrl);
    }
}
