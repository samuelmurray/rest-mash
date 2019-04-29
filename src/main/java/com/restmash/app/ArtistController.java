package com.restmash.app;

import com.restmash.musicbrainz.MusicBrainzContent;
import com.restmash.musicbrainz.MusicBrainzContentFactory;
import com.restmash.wikipedia.WikipediaContent;
import com.restmash.wikipedia.WikipediaContentFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;
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
        addCoverArtToAlbumsWithService();
        shutdownServiceAndAwaitTermination();
        createWikipediaContentWithService();
        return new Artist(mbid, musicBrainzContent, wikipediaContent);
    }

    private void addCoverArtToAlbumsWithService() {
        AddCoverArtToAlbumsRunnable addCoverArtToAlbumsTask = new AddCoverArtToAlbumsRunnable(musicBrainzContent);
        service.execute(addCoverArtToAlbumsTask);
    }

    private void createWikipediaContentWithService() {
        CreateWikipediaContentCallable createWikipediaContentTask = new CreateWikipediaContentCallable(musicBrainzContent);
        Future<WikipediaContent> future = service.submit(createWikipediaContentTask);
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

    private WikipediaContent createWikipediaContent() {
        try {
            return createWikipediaContentSafe();
        } catch (NoSuchElementException | URISyntaxException e) {
            System.err.println(String.format("WikipediaContent not created due to Exception: %s", e));
            return null;
        }
    }

    private WikipediaContent createWikipediaContentSafe() throws URISyntaxException {
        try {
            return createWikipediaContentFromTitle();
        } catch (NoSuchElementException e) {
            return createWikipediaContentFromId();
        }
    }

    private WikipediaContent createWikipediaContentFromTitle() throws URISyntaxException {
        String title = musicBrainzContent.getWikipediaTitle();
        return WikipediaContentFactory.createFromWikipediaTitle(title);
    }

    private WikipediaContent createWikipediaContentFromId() throws URISyntaxException {
        String wikidataId = musicBrainzContent.getWikidataId();
        return WikipediaContentFactory.createFromWikidataId(wikidataId);
    }
}
