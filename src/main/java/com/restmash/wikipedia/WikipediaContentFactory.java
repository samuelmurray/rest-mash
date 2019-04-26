package com.restmash.wikipedia;

import com.restmash.wikidata.WikidataContent;
import com.restmash.wikidata.WikidataContentFactory;

import org.springframework.web.client.RestTemplate;

public class WikipediaContentFactory {
    private static final String URL_PREFIX = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";
    private static final RestTemplate restTemplate = new RestTemplate();

    private WikipediaContentFactory() {
    }

    public static WikipediaContent createFromWikipediaTitle(String wikipediaTitle) {
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
