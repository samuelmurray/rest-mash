package com.restmash.app;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class TestArtist {
    @Test
    void testGetMbid() {
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        assertEquals(mbid, artist.getMbid());
    }

    @Test
    void testGetName() {
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        String expected = "In Solitude";
        assertEquals(expected, artist.getName());
    }

    @Test
    void testGetAlbumsMissing() {
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        Album[] expected = new Album[]{};
        assertArrayEquals(expected, artist.getAlbums());
    }

    @Test
    void testGetDescription() {
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        String expectedSubString = "Swedish heavy metal band from Uppsala";
        assertTrue(artist.getDescription().contains(expectedSubString));
    }

    @Test
    void testGetDescriptionMissing() {
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        String expected = "";
        assertEquals(expected, artist.getDescription());
    }
}
