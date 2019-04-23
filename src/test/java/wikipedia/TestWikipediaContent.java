package wikipedia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

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

    @Test
    public void testGetQueryNull() {
        WikipediaContent content = new WikipediaContent();
        assertNull(content.getQuery());
    }

    @Test
    public void testGetQuery() {
        WikipediaContent content = new WikipediaContent();
        Map<String, Object> query = new HashMap<>();
        query.put("Key", null);
        content.setQuery(query);
        assertEquals(query, content.getQuery());
    }
}
