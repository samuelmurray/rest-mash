package musicbrainz;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class TestMusicBrainzRelation {

    @Test
    void testGetType() {
        MusicBrainzRelation relation = new MusicBrainzRelation();
        String type = "wikipedia";
        relation.setType(type);
        assertEquals(type, relation.getType());
    }

    @Test
    void testGetTypeNull() {
        MusicBrainzRelation relation = new MusicBrainzRelation();
        assertNull(relation.getType());
    }

    @Test
    void testGetUrl() {
        MusicBrainzUrl url = new MusicBrainzUrl();
        MusicBrainzRelation relation = new MusicBrainzRelation();
        relation.setUrl(url);
        assertEquals(url, relation.getUrl());
    }

    @Test
    void testGetUrlNull() {
        MusicBrainzRelation relation = new MusicBrainzRelation();
        assertNull(relation.getUrl());
    }
}
