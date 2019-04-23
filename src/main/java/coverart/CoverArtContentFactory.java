package coverart;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CoverArtContentFactory {
    private static final String URL_PREFIX = "http://coverartarchive.org/release-group/";

    private CoverArtContentFactory() {
    }

    public static CoverArtContent createFromMbid(String mbid) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = buildUrl(mbid);
            return restTemplate.getForObject(url, CoverArtContent.class);
        } catch (HttpClientErrorException ex) {
            System.err.println(ex.toString());
            System.err.println(mbid);
            return null;
        }
    }

    private static String buildUrl(String mbid) {
        return URL_PREFIX + mbid;
    }
}