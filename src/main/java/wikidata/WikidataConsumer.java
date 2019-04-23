package wikidata;

import org.springframework.web.client.RestTemplate;

public class WikidataConsumer {
    private static final String urlPrefix = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=";
    private static final String urlSuffix = "&format=json&props=sitelinks";
    private RestTemplate restTemplate;
    private WikidataContent content;


    public WikidataConsumer(String wikidataId) {
        restTemplate = new RestTemplate();
        consume(wikidataId);
    }

    private void consume(String wikidataId) {
        content = restTemplate.getForObject(buildUrl(wikidataId), WikidataContent.class);
    }

    public WikidataContent getContent() {
        return content;
    }

    private String buildUrl(String wikidataId) {
        return urlPrefix + wikidataId + urlSuffix;
    }
}

