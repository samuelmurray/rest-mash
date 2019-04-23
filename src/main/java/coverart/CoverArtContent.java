package coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArtContent {

    private Image[] images;

    public CoverArtContent() {
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public Image[] getImages() {
        return images;
    }
}
