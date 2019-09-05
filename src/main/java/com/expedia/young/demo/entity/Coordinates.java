
package com.expedia.young.demo.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "center_longitude",
    "center_latitude",
    "bounding_polygon"
})
public class Coordinates {

    @JsonProperty("center_longitude")
    private Double centerLongitude;
    @JsonProperty("center_latitude")
    private Double centerLatitude;
    @JsonProperty("bounding_polygon")
    private BoundingPolygon boundingPolygon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("center_longitude")
    public Double getCenterLongitude() {
        return centerLongitude;
    }

    @JsonProperty("center_longitude")
    public void setCenterLongitude(Double centerLongitude) {
        this.centerLongitude = centerLongitude;
    }

    @JsonProperty("center_latitude")
    public Double getCenterLatitude() {
        return centerLatitude;
    }

    @JsonProperty("center_latitude")
    public void setCenterLatitude(Double centerLatitude) {
        this.centerLatitude = centerLatitude;
    }

    @JsonProperty("bounding_polygon")
    public BoundingPolygon getBoundingPolygon() {
        return boundingPolygon;
    }

    @JsonProperty("bounding_polygon")
    public void setBoundingPolygon(BoundingPolygon boundingPolygon) {
        this.boundingPolygon = boundingPolygon;
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
