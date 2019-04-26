package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.musicbrainz.MusicBrainzContentFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    @RequestMapping("/artist")
    public Artist artist(@RequestParam(value = "mbid") String mbid) {
        MusicBrainzContent content = MusicBrainzContentFactory.createFromMbid(mbid);
        content.addCoverArtToAlbums();
        Artist artist = new Artist(mbid, content);
        return artist;
    }
}
