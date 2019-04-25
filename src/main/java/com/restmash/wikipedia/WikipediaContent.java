package com.restmash.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.NoSuchElementException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaContent {
    private Map<String, Object> query;

    public Map<String, Object> getQuery() {
        return query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }

    public String getExtract() {
        // FIXME: Unchecked casts; no error handling
        Map<String, Object> pages = (Map<String, Object>) query.get("pages");
        for (Object value : pages.values()) {
            Map<String, Object> valueAsMap = (Map<String, Object>) value;
            if (valueAsMap.containsKey("extract")) {
                return (String) valueAsMap.get("extract");
            }
        }
        throw new NoSuchElementException("Extract missing");
    }
}
