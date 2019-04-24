package wikipedia;

import org.springframework.web.client.RestTemplate;

public class WikipediaContentFactory {
    private static final String URL_PREFIX = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";

    private WikipediaContentFactory() {
    }

    public static WikipediaContent createFromWikipediaTitle(String wikipediaTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildUrl(wikipediaTitle);
        return restTemplate.getForObject(url, WikipediaContent.class);
    }

    private static String buildUrl(String wikipediaTitle) {
        return URL_PREFIX + wikipediaTitle;  // TODO: This seems to work without URL encoding the title
    }
}
