package app;

import org.springframework.web.client.RestTemplate;

public class WikiDataConsumer {
    private static final String urlPrefix = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=";
    private static final String urlSuffix = "&format=json&props=sitelinks";
    private RestTemplate restTemplate;
    private WikiDataContent content;


    public WikiDataConsumer(String wikiDataId) {
        restTemplate = new RestTemplate();
        consume(wikiDataId);
    }

    private void consume(String wikiDataId) {
        content = restTemplate.getForObject(buildUrl(wikiDataId), WikiDataContent.class);
    }

    public WikiDataContent getContent() {
        return content;
    }

    private String buildUrl(String wikiDataId) {
        return urlPrefix + wikiDataId + urlSuffix;
    }
}

