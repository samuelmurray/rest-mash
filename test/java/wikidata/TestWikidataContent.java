package wikidata;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestWikidataContent {

    @Test
    public void testGetEnwikiTitleVeronicaMaggio() {
        String id = "Q260597";
        WikidataContent content = WikidataContentFactory.createFromWikidataId(id);
        assertEquals("Veronica Maggio", content.getEnwikiTitle(id));
    }

    @Test
    public void testGetEnwikiTitleNirvana() {
        String id = "Q11649";
        WikidataContent content = WikidataContentFactory.createFromWikidataId(id);
        assertEquals("Nirvana (band)", content.getEnwikiTitle(id));
    }

}
