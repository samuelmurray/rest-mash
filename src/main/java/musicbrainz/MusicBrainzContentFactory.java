package musicbrainz;

import org.springframework.web.client.RestTemplate;

public class MusicBrainzContentFactory {
    private static final String urlPrefix = "http://musicbrainz.org/ws/2/artist/";
    private static final String urlSuffix = "?&fmt=json&inc=url-rels+release-groups";

    private MusicBrainzContentFactory() {
    }

    public static MusicBrainzContent createFromMbid(String mbid) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildUrl(mbid);
        return restTemplate.getForObject(url, MusicBrainzContent.class);
    }

    private static String buildUrl(String mbid) {
        return urlPrefix + mbid + urlSuffix;
    }
}
