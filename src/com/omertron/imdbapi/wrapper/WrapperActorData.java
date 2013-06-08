package com.omertron.imdbapi.wrapper;

import com.omertron.imdbapi.model.ImdbPerson;
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
public class WrapperActorData {

    private static final Logger LOGGER = Logger.getLogger(WrapperActorData.class);
    @JsonProperty("data")
    private ImdbPerson actorData;

    public ImdbPerson getActorData() {
        return actorData;
    }

    public void setActorData(ImdbPerson actorData) {
        this.actorData = actorData;
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
