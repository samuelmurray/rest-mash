package app;

import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
