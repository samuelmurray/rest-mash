package coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArtContent {
    private CoverArtImage[] images;

    public CoverArtImage[] getImages() {
        return images;
    }

    public void setImages(CoverArtImage[] images) {
        this.images = images;
    }
}
