package com.omertron.imdbapi.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omertron.imdbapi.search.SearchObject;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class ImdbSearchResult {

    private static final Logger LOGGER = Logger.getLogger(ImdbSearchResult.class);
    @JsonProperty("label")
    private String label = "";
    @JsonProperty("list")
    private List<SearchObject> searchObject = Collections.EMPTY_LIST;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SearchObject> getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(List<SearchObject> searchObject) {
        this.searchObject = searchObject;
    }

    @Override
    public String toString() {
        return "ImdbSearchResult{" + "label=" + label + ", searchObject=" + searchObject + '}';
    }

    /**
     * Handle unknown properties and print a message
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown property: '").append(key);
        sb.append("' value: '").append(value).append("'");
        LOGGER.trace(sb.toString());
    }
}
