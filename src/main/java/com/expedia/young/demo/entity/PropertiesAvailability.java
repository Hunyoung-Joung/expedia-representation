
package com.expedia.young.demo.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "property_id",
    "rooms",
    "links",
    "score"
})
public class PropertiesAvailability {

    @JsonProperty("property_id")
    private String propertyId;
    @JsonProperty("rooms")
    private List<Room> rooms = null;
    @JsonProperty("links")
    private Links__ links;
    @JsonProperty("score")
    private String score;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("property_id")
    public String getPropertyId() {
        return propertyId;
    }

    @JsonProperty("property_id")
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @JsonProperty("rooms")
    public List<Room> getRooms() {
        return rooms;
    }

    @JsonProperty("rooms")
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @JsonProperty("links")
    public Links__ getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links__ links) {
        this.links = links;
    }

    @JsonProperty("score")
    public String getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(String score) {
        this.score = score;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
