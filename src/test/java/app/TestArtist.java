package app;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestArtist {
    @Test
    void testGetMbid() {
        long id = 0L;
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        Artist artist = new Artist(id, mbid);
        assertEquals(mbid, artist.getMbid());
    }

    @Test
    void testGetName() {
        long id = 0L;
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        Artist artist = new Artist(id, mbid);
        String expected = "In Solitude";
        assertEquals(expected, artist.getName());
    }

    @Test
    void testGetAlbumsMissing() {
        long id = 0L;
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        Artist artist = new Artist(id, mbid);
        Album[] expected = new Album[]{};
        assertArrayEquals(expected, artist.getAlbums());
    }

    @Test
    void testGetDescription() {
        long id = 0L;
        String mbid = "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7";
        Artist artist = new Artist(id, mbid);
        String expectedSubString = "Swedish heavy metal band from Uppsala";
        assertTrue(artist.getDescription().contains(expectedSubString));
    }

    @Test
    void testGetDescriptionMissing() {
        long id = 0L;
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        Artist artist = new Artist(id, mbid);
        String expected = "";
        assertEquals(expected, artist.getDescription());
    }
}
