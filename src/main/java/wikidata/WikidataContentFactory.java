package wikidata;

import org.springframework.web.client.RestTemplate;

public class WikidataContentFactory {
    private static final String urlPrefix = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=";
    private static final String urlSuffix = "&format=json&props=sitelinks";

    private WikidataContentFactory() {
    }

    public static WikidataContent createFromWikidataId(String wikidataId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildUrl(wikidataId);
        return restTemplate.getForObject(url, WikidataContent.class);
    }

    private static String buildUrl(String wikidataId) {
        return urlPrefix + wikidataId + urlSuffix;
    }
}
