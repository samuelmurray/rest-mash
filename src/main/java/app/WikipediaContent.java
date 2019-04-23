package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

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
        Map<String, Object> pages = (Map<String, Object>) query.get("pages");
        for (Object value : pages.values()) {
            Map<String, Object> valueAsMap = (Map<String, Object>) value;
            if (valueAsMap.containsKey("extract")) {
                return (String) valueAsMap.get("extract");
            }
        }
        throw new RuntimeException("Extract missing");
    }
}
