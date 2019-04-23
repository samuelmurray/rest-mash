package coverart;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CoverArtConsumer {
    private static final String URL_PREFIX = "http://coverartarchive.org/release-group/";
    private RestTemplate restTemplate;
    private CoverArtContent content;

    public CoverArtConsumer(String mbid) {
        restTemplate = new RestTemplate();
        consume(mbid);
    }

    public CoverArtContent getContent() {
        return content;
    }

    private void consume(String mbid) {
        try {
            content = restTemplate.getForObject(buildUrl(mbid), CoverArtContent.class);
        } catch (HttpClientErrorException ex) {
            System.err.println(ex.toString());
            System.err.println(mbid);
        }
    }

    private String buildUrl(String mbid) {
        return URL_PREFIX + mbid;
    }
}