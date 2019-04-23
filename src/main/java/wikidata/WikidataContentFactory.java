package wikidata;

import org.springframework.web.client.RestTemplate;

public class WikidataContentFactory {
    private static final String urlPrefix = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=";
    private static final String urlSuffix = "&format=json&props=sitelinks";
    private RestTemplate restTemplate;
    private WikidataContent content;


    public WikidataContentFactory(String wikidataId) {
        restTemplate = new RestTemplate();
        consume(wikidataId);
    }

    public static WikidataContent createFromWikidataId(String wikidataId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(buildUrl(wikidataId), WikidataContent.class);
    }


    private void consume(String wikidataId) {
        content = restTemplate.getForObject(buildUrl(wikidataId), WikidataContent.class);
    }

    public WikidataContent getContent() {
        return content;
    }

    private static String buildUrl(String wikidataId) {
        return urlPrefix + wikidataId + urlSuffix;
    }
}

