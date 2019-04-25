package wikidata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikidataContent {
    private Map<String, Object> entities;

    public Map<String, Object> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, Object> entities) {
        this.entities = entities;
    }

    public String getWikipediaTitle(String wikidataId) {
        // FIXME: Unchecked casts; no error handling
        Map<String, Object> wikiIdMap = (Map<String, Object>) entities.get(wikidataId);
        Map<String, Object> sitelinks = (Map<String, Object>) wikiIdMap.get("sitelinks");
        Map<String, Object> enwiki = (Map<String, Object>) sitelinks.get("enwiki");
        return (String) enwiki.get("title");
    }
}
