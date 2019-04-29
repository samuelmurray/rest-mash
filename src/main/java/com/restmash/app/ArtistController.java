package com.restmash.app;

import com.restmash.musicbrainz.AddCoverArtToAlbumsRunnable;
import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.musicbrainz.MusicBrainzContentFactory;
import com.restmash.wikipedia.WikipediaContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class ArtistController {
    private MusicBrainzContent musicBrainzContent;
    private WikipediaContent wikipediaContent;
    private ExecutorService service;

    @RequestMapping("/artist")
    public Artist artist(@RequestParam(value = "mbid") String mbid) {
        musicBrainzContent = MusicBrainzContentFactory.createFromMbid(mbid);
        service = Executors.newCachedThreadPool();
        addCoverArtToAlbums();
        createWikipediaContent();
        shutdownServiceAndAwaitTermination();
        return new Artist(mbid, musicBrainzContent, wikipediaContent);
    }

    private void addCoverArtToAlbums() {
        service.execute(new AddCoverArtToAlbumsRunnable(musicBrainzContent));
    }

    private void createWikipediaContent() {
        Future<WikipediaContent> future = service.submit(new CreateWikipediaContentCallable(musicBrainzContent));
        try {
            wikipediaContent = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void shutdownServiceAndAwaitTermination() {
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
