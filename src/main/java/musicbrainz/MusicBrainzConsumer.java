package musicbrainz;

import org.springframework.web.client.RestTemplate;

public class MusicBrainzConsumer {
    private static final String urlPrefix = "http://musicbrainz.org/ws/2/artist/";
    private static final String urlSuffix = "?&fmt=json&inc=url-rels+release-groups";
    private RestTemplate restTemplate;
    private MusicBrainzContent content;

    public MusicBrainzConsumer(String mbid) {
        restTemplate = new RestTemplate();
        consume(mbid);
    }

    public static MusicBrainzContent createFromMbid(String mbid) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(buildUrl(mbid), MusicBrainzContent.class);
    }

    private void consume(String mbid) {
        content = restTemplate.getForObject(buildUrl(mbid), MusicBrainzContent.class);
    }

    public MusicBrainzContent getContent() {
        return content;
    }

    private static String buildUrl(String mbid) {
        return urlPrefix + mbid + urlSuffix;
    }
}
