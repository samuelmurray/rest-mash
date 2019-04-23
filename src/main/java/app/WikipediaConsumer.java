package app;

import org.springframework.web.client.RestTemplate;

public class WikipediaConsumer {
    private static final String urlPrefix = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";
    private RestTemplate restTemplate;
    private WikipediaContent content;


    public WikipediaConsumer(String wikipediaTitle) {
        restTemplate = new RestTemplate();
        consume(wikipediaTitle);
    }

    private void consume(String wikipediaTitle) {
        content = restTemplate.getForObject(buildUrl(wikipediaTitle), WikipediaContent.class);
    }

    public WikipediaContent getContent() {
        return content;
    }

    private String buildUrl(String wikipediaTitle) {
        return urlPrefix + wikipediaTitle;  // TODO: This seems to work without URL encoding the title
    }
}
