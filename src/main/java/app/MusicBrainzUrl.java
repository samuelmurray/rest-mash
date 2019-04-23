package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.net.URISyntaxException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzUrl {
    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String lastPartOfUrl() throws URISyntaxException {
        URI uri = new URI(getResource());
        System.out.println(uri.toString());
        String[] segments = uri.getPath().split("/");
        return segments[segments.length - 1];
    }
}
