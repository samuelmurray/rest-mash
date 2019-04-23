package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzRelation {
    private String type;
    private MusicBrainzUrl url;

    public MusicBrainzUrl getUrl() {
        return url;
    }

    public void setUrl(MusicBrainzUrl url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
