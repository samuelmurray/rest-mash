package wikipedia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class TestWikipediaContent {

    @Test
    public void testGetExtractVeronicaMaggio() {
        String title = "Veronica Maggio";
        WikipediaContent content = WikipediaContentFactory.createFromWikipediaTitle(title);
        String expectedSubString = "Veronica Sandra Karin Maggio";
        assertTrue(content.getExtract().contains(expectedSubString));
    }

    @Test
    public void testGetExtractSamuRuotsalainen() {
        // Artist has no wikipedia page
        String title = "Samu Ruotsalainen";
        WikipediaContent content = WikipediaContentFactory.createFromWikipediaTitle(title);
        Assertions.assertThrows(NoSuchElementException.class, content::getExtract);
    }
}
