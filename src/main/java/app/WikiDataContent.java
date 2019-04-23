package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiDataContent {
    private Map<String, Object> entities;

    public Map<String, Object> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, Object> entities) {
        this.entities = entities;
    }

    public String getEnwikiTitle(String wikiId) {
        Map<String,Object> wikiIdMap = (Map<String,Object>)entities.get(wikiId);
        Map<String,Object> sitelinks = (Map<String,Object>)wikiIdMap.get("sitelinks");
        Map<String,Object> enwiki = (Map<String,Object>)sitelinks.get("enwiki");
        System.out.println((String)enwiki.get("title"));
        return (String)enwiki.get("title");
    }
}
