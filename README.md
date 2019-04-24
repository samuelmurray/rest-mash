# rest-mash

An implementation of a REST API that wraps multiple other APIs. Written in Java using Spring Boot and Maven.

[![Build Status](https://travis-ci.com/samuelmurray/rest-mash.svg?token=metTeQBqcky3teaepvwx&branch=master)](https://travis-ci.com/samuelmurray/rest-mash)

## Goal
The aim of this project is to make a REST based API that - given a MusicBrainz Identifier (MBID) - returns an artists name, Wikipedia description, and list of albums with links to cover images.
This is done by calling other public APIs: [MusicBrainz](http://musicbrainz.org/ws/2), [Cover Art Archive](http://coverartarchive.org/), [Wikidata](https://www.wikidata.org/w/api.php) and [Wikipedia](https://en.wikipedia.org/w/api.php).