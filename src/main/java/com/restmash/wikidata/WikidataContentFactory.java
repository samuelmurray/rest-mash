package com.restmash.wikidata;

import org.springframework.web.client.RestTemplate;

public class WikidataContentFactory {
    private static final String URL_PREFIX = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=";
    private static final String URL_SUFFIX = "&format=json&props=sitelinks";

    private WikidataContentFactory() {
    }

    public static WikidataContent createFromWikidataId(String wikidataId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildUrl(wikidataId);
        return restTemplate.getForObject(url, WikidataContent.class);
    }

    private static String buildUrl(String wikidataId) {
        return URL_PREFIX + wikidataId + URL_SUFFIX;
    }
}
