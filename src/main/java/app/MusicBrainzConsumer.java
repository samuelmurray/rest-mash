package app;

import org.springframework.web.client.RestTemplate;

public class MusicBrainzConsumer {
    private static final String urlPrefix = "http://musicbrainz.org/ws/2/artist/";
    private static final String urlSuffix = "?&fmt=json&inc=url-rels+release-groups";
    private RestTemplate restTemplate;
    private MusicBrainzContent value;

    public MusicBrainzConsumer(String mbid) {
        restTemplate = new RestTemplate();
        consume(mbid);
    }

    private void consume(String mbid) {
        value = restTemplate.getForObject(buildUrl(mbid), MusicBrainzContent.class);
    }

    public MusicBrainzContent getValue() {
        return value;
    }

    private String buildUrl(String mbid){
        return urlPrefix + mbid + urlSuffix;
    }
}
