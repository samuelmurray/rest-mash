package coverart;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CoverArtContentFactory {
    private static final String URL_PREFIX = "http://coverartarchive.org/release-group/";
    private RestTemplate restTemplate;
    private CoverArtContent content;

    public CoverArtContentFactory(String mbid) {
        restTemplate = new RestTemplate();
        consume(mbid);
    }

    public static CoverArtContent createFromMbid(String mbid) {
        CoverArtContentFactory consumer = new CoverArtContentFactory(mbid);
        return consumer.content;
    }

    private void consume(String mbid) {
        try {
            content = restTemplate.getForObject(buildUrl(mbid), CoverArtContent.class);
        } catch (HttpClientErrorException ex) {
            System.err.println(ex.toString());
            System.err.println(mbid);
        }
    }

    private static String buildUrl(String mbid) {
        return URL_PREFIX + mbid;
    }
}