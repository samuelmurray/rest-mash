package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    private String url;

    public Image() {
    }

    @JsonProperty(value = "image", defaultValue = "MISSING")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
