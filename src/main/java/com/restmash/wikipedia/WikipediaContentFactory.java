package com.restmash.wikipedia;

import org.springframework.web.client.RestTemplate;
import com.restmash.wikidata.WikidataContent;
import com.restmash.wikidata.WikidataContentFactory;

public class WikipediaContentFactory {
    private static final String URL_PREFIX = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";

    private WikipediaContentFactory() {
    }

    public static WikipediaContent createFromWikipediaTitle(String wikipediaTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildUrl(wikipediaTitle);
        return restTemplate.getForObject(url, WikipediaContent.class);
    }

    public static WikipediaContent createFromWikidataId(String wikidataId) {
        String wikipediaTitle = createTitleFromWikidataId(wikidataId);
        return createFromWikipediaTitle(wikipediaTitle);
    }

    private static String createTitleFromWikidataId(String wikidataId) {
        WikidataContent content = WikidataContentFactory.createFromWikidataId(wikidataId);
        return content.getWikipediaTitle(wikidataId);
    }

    private static String buildUrl(String wikipediaTitle) {
        return URL_PREFIX + wikipediaTitle;  // This seems to work without URL encoding the title
    }
}
