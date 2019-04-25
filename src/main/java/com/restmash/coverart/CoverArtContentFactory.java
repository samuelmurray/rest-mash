package com.restmash.coverart;

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
        } catch (HttpClientErrorException e) {
            System.err.println(String.format("CoverArtContent for %s not created due to HttpClientErrorException: %s", mbid, e));
            return null;
        }
    }

    private static String buildUrl(String mbid) {
        return URL_PREFIX + mbid;
    }
}