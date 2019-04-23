package wikipedia;

import org.springframework.web.client.RestTemplate;

public class WikipediaContentFactory {
    private static final String urlPrefix = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";
    private RestTemplate restTemplate;
    private WikipediaContent content;


    public WikipediaContentFactory(String wikipediaTitle) {
        restTemplate = new RestTemplate();
        consume(wikipediaTitle);
    }

    private void consume(String wikipediaTitle) {
        String url = buildUrl(wikipediaTitle);
        content = restTemplate.getForObject(url, WikipediaContent.class);
    }

    public WikipediaContent getContent() {
        return content;
    }

    private static String buildUrl(String wikipediaTitle) {
        return urlPrefix + wikipediaTitle;  // TODO: This seems to work without URL encoding the title
    }
}
