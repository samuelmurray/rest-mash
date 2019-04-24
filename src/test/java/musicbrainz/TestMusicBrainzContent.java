package musicbrainz;

import app.Album;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMusicBrainzContent {

    @Test
    void testGetAlbums() {
        MusicBrainzContent content = new MusicBrainzContent();
        Album album = new Album();
        Album[] albums = new Album[]{album};
        content.setAlbums(albums);
        assertArrayEquals(albums, content.getAlbums());
    }

    @Test
    void testGetAlbumsNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getAlbums());
    }

    @Test
    void testGetName() {
        MusicBrainzContent content = new MusicBrainzContent();
        String name = "name";
        content.setName(name);
        assertEquals(name, content.getName());
    }

    @Test
    void testGetNameNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getName());
    }

    @Test
    void testGetRelations() {
        MusicBrainzContent content = new MusicBrainzContent();
        MusicBrainzRelation relation = new MusicBrainzRelation();
        MusicBrainzRelation[] relations = new MusicBrainzRelation[]{relation};
        content.setRelations(relations);
        assertArrayEquals(relations, content.getRelations());
    }

    @Test
    void testGetRelationsNull() {
        MusicBrainzContent content = new MusicBrainzContent();
        assertNull(content.getRelations());
    }
}
