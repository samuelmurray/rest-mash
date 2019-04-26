package com.restmash.coverart;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CoverArtContentFactory {
    private static final String URL_PREFIX = "http://coverartarchive.org/release-group/";
    private static final RestTemplate restTemplate = new RestTemplate();

    private CoverArtContentFactory() {
    }

    public static CoverArtContent createFromMbid(String mbid) {
        try {
            String url = buildUrl(mbid);
            return restTemplate.getForObject(url, CoverArtContent.class);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    private static String buildUrl(String mbid) {
        return URL_PREFIX + mbid;
    }
}