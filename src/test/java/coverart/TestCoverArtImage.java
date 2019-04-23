package coverart;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestCoverArtImage {
    @Test
    public void testGetUrl() {
        CoverArtImage image = new CoverArtImage();
        String url = "https://www.google.com/";
        image.setUrl(url);
        assertEquals(url, image.getUrl());
    }
}
