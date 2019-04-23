package coverart;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestCoverArtImage {

    @Test
    public void testGetUrlNull() {
        CoverArtImage image = new CoverArtImage();
        assertNull(image.getUrl());
    }

    @Test
    public void testGetUrl() {
        CoverArtImage image = new CoverArtImage();
        String url = "https://www.google.com/";
        image.setUrl(url);
        assertEquals(url, image.getUrl());
    }
}
