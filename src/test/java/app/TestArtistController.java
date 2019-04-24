package app;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class TestArtistController {

    @Test
    void testArtist() {
        String mbid = "09302d63-d57f-4fe0-9c37-095843b91dff";
        ArtistController controller = new ArtistController();
        Artist artist = controller.artist(mbid);
        assertEquals(mbid, artist.getMbid());
    }
}
