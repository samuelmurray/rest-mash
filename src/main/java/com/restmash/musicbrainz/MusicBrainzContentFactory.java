package com.restmash.musicbrainz;

import org.springframework.web.client.RestTemplate;

public class MusicBrainzContentFactory {
    private static final String URL_PREFIX = "http://musicbrainz.org/ws/2/artist/";
    private static final String URL_SUFFIX = "?&fmt=json&inc=url-rels+release-groups";
    private static final RestTemplate restTemplate = new RestTemplate();

    private MusicBrainzContentFactory() {
    }

    public static MusicBrainzContent createFromMbid(String mbid) {
        String url = buildUrl(mbid);
        return restTemplate.getForObject(url, MusicBrainzContent.class);
    }

    private static String buildUrl(String mbid) {
        return URL_PREFIX + mbid + URL_SUFFIX;
    }
}
