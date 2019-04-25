package com.restmash.wikipedia;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

class TestWikipediaContent {

    @Test
    void testGetExtractVeronicaMaggio() {
        String title = "Veronica Maggio";
        WikipediaContent content = WikipediaContentFactory.createFromWikipediaTitle(title);
        String expectedSubString = "Veronica Sandra Karin Maggio";
        assertTrue(content.getExtract().contains(expectedSubString));
    }

    @Test
    void testGetExtractSamuRuotsalainen() {
        // Artist has no com.restmash.wikipedia page
        String title = "Samu Ruotsalainen";
        WikipediaContent content = WikipediaContentFactory.createFromWikipediaTitle(title);
        Assertions.assertThrows(NoSuchElementException.class, content::getExtract);
    }

    @Test
    void testGetQueryNull() {
        WikipediaContent content = new WikipediaContent();
        assertNull(content.getQuery());
    }

    @Test
    void testGetQuery() {
        WikipediaContent content = new WikipediaContent();
        Map<String, Object> query = new HashMap<>();
        query.put("Key", null);
        content.setQuery(query);
        assertEquals(query, content.getQuery());
    }
}
