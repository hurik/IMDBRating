package com.omertron.imdbapi.wrapper;

import com.omertron.imdbapi.model.ImdbQuotes;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON Wrapper class for the response from the API
 *
 * Not intended for use outside of the API
 *
 * @author stuart.boston
 */
@JsonIgnoreProperties({"@meta","exp","copyright"})
public class WrapperQuotes {

    private static final Logger LOGGER = Logger.getLogger(WrapperQuotes.class);
    @JsonProperty("data")
    private ImdbQuotes quotes;

    public ImdbQuotes getQuotes() {
        return quotes;
    }

    public void setQuotes(ImdbQuotes quotes) {
        this.quotes = quotes;
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
        LOGGER.warn(sb.toString());
    }
}
