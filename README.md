# rest-mash

An implementation of a REST API that wraps multiple other APIs. Written in Java using Spring Boot and Maven.

[![Build Status](https://travis-ci.com/samuelmurray/rest-mash.svg?token=metTeQBqcky3teaepvwx&branch=master)](https://travis-ci.com/samuelmurray/rest-mash)

## Goal
The aim of this project is to make a REST based API that - given a MusicBrainz Identifier (MBID) - returns an artists name, Wikipedia description, and list of albums with links to cover images.
This is done by calling other public APIs: [MusicBrainz](http://musicbrainz.org/ws/2), [Cover Art Archive](http://coverartarchive.org/), [Wikidata](https://www.wikidata.org/w/api.php) and [Wikipedia](https://en.wikipedia.org/w/api.php).

## Requirements
The code is tested with Maven 3.6, and runs on Java 8, as specified in `pom.xml`. On macOS, this can be installed using [Homebrew](https://brew.sh/):

```
$ brew cask install java8
$ brew install maven
```

However, all tests run successfully on Linux and Mac using TravisCI.

## Run

### Docker
We provide a docker container for this project - samuelmurray/rest-mash. Run it with the following command:
```
$ docker run -p 8080:8080 samuelmurray/rest-mash:latest
``` 

### Maven
First, clone the repository.

```
$ git clone git@github.com:samuelmurray/rest-mash.git
$ cd rest-mash
```

To run the code, we use Maven and Spring Boot. It can either be run directly:

```
$ mvn spring-boot:run
```

Alternatively, you can build a JAR file and run that:

```
$ mvn clean package
$ java -jar target/gs-rest-service-0.1.0.jar
```

This will run all tests prior to building the JAR, which might take a while - see Tests section below.

Either way you run the application, it will start a service on `localhost:8080`.
The controller handles requests to `/artist`, and requires a MBID to be given.
To search for an artist with MBID _abc-123_, enter http://localhost:8080/artist?mbid=abc-123 in your web browser.

### Example
For example, http://localhost:8080/artist?mbid=aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7 gives information on a Swedish band _In Solitude_.

```json
{
  "mbid": "aa2497f9-d4b3-4d03-9a1c-bb7d76acc1b7",
  "name": "In Solitude",
  "description": "<p><b>In Solitude</b> was a Swedish heavy metal band from Uppsala, Sweden. </p>",
  "albums": [
    {
      "title": "The World.The Flesh.The Devil",
      "images": null,
      "id": "4de7f50b-0a20-47bd-b29b-67568d1a866d"
    },
    {
      "title": "Sister",
      "images": [
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296169384.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296172295.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296174919.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296176554.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296180315.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296181666.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296184332.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296187337.jpg"
        },
        {
          "image": "http://coverartarchive.org/release/38c8b91d-ae41-4f4b-ba45-8d0259f97759/13296189168.jpg"
        }
      ],
      "id": "8e76ab42-d5ca-402d-8728-1f7fe85fdf41"
    },
    {
      "title": "In Solitude",
      "images": [
        {
          "image": "http://coverartarchive.org/release/41c2af52-cd47-44c9-a081-ef0840887995/6856826890.jpg"
        }
      ],
      "id": "d8009340-8afc-301e-955c-6cd18a94bbaa"
    }
  ]
}
```

## Tests
There is a suite of tests in `src/test/java`. In order to run these, use Maven in the project root:

```
$ mvn test
```

Note that there are both unit tests and integration tests, and so running all tests might take a while, and requires an internet connection.
A future improvement - apart from checking more corner cases - would be to separate unit tests and integration tests.
Some features could be tested with mocking, to speed up tests and not rely on third party APIs.

## References
This project served as a practice for me, in Java, Maven and Spring Boot. I based the structure on two tutorials from spring.io: 
one on [building a REST service](https://spring.io/guides/gs/rest-service/) and one on [consuming an existing service](https://spring.io/guides/gs/consuming-rest/).