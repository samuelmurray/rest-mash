# rest-mash

An implementation of a REST API that wraps multiple other APIs. Written in Java using Spring Boot and Maven.

[![Build Status](https://travis-ci.com/samuelmurray/rest-mash.svg?token=metTeQBqcky3teaepvwx&branch=master)](https://travis-ci.com/samuelmurray/rest-mash)

## Goal
The aim of this project is to make a REST based API that - given a MusicBrainz Identifier (MBID) - returns an artists name, Wikipedia description, and list of albums with links to cover images.
This is done by calling other public APIs: [MusicBrainz](http://musicbrainz.org/ws/2), [Cover Art Archive](http://coverartarchive.org/), [Wikidata](https://www.wikidata.org/w/api.php) and [Wikipedia](https://en.wikipedia.org/w/api.php).

## Run
To run the code, we use Maven and Spring Boot. The recommended way is to run it directly: 

```
$ mvn spring-boot:run
```

Alternatively, you can build a JAR file and run that:

```
$ mvn clean package
$ java -jar target/gs-rest-service-0.1.0.jar
```

This will run all tests prior to building the JAR, which might take a while - see Tests section below.

## Tests
There is a suite of tests in `src/test/java`. In order to run these, use Maven in the project root:

```
$ mvn test
```

Note that there are both unit tests and integration tests, and so running all tests might take a while, and requires an internet connection.
A future improvement - apart from checking more corner cases - would be to separate unit tests and integration tests. 
Some features could be tested with mocking, to speed up tests and not rely on third party APIs.
