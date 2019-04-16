package app;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/artist")
    public Artist artist(@RequestParam(value = "mbid") String mbid) {
        return new Artist(counter.incrementAndGet(), mbid);
    }
}
