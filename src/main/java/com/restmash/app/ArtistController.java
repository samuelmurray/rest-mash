package com.restmash.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    @RequestMapping("/artist")
    public Artist artist(@RequestParam(value = "mbid") String mbid) {
        return new Artist(mbid);
    }
}
