
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
    "high_level_region",
    "city",
    "neighborhood",
    "point_of_interest"
})
public class Descendants {

    @JsonProperty("high_level_region")
    private List<String> highLevelRegion = null;
    @JsonProperty("city")
    private List<String> city = null;
    @JsonProperty("neighborhood")
    private List<String> neighborhood = null;
    @JsonProperty("point_of_interest")
    private List<String> pointOfInterest = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("high_level_region")
    public List<String> getHighLevelRegion() {
        return highLevelRegion;
    }

    @JsonProperty("high_level_region")
    public void setHighLevelRegion(List<String> highLevelRegion) {
        this.highLevelRegion = highLevelRegion;
    }

    @JsonProperty("city")
    public List<String> getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(List<String> city) {
        this.city = city;
    }

    @JsonProperty("neighborhood")
    public List<String> getNeighborhood() {
        return neighborhood;
    }

    @JsonProperty("neighborhood")
    public void setNeighborhood(List<String> neighborhood) {
        this.neighborhood = neighborhood;
    }

    @JsonProperty("point_of_interest")
    public List<String> getPointOfInterest() {
        return pointOfInterest;
    }

    @JsonProperty("point_of_interest")
    public void setPointOfInterest(List<String> pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
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
