
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
    "id",
    "type",
    "name",
//    "name_full",
    "country_code",
    "coordinates",
    "ancestors",
    "descendants",
    "property_ids"
})
public class Region {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
//    @JsonProperty("name_full")
//    private String nameFull;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("ancestors")
    private List<Ancestor> ancestors = null;
    @JsonProperty("descendants")
    private Descendants descendants;
    @JsonProperty("property_ids")
    private List<String> propertyIds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
//
//    @JsonProperty("name_full")
//    public String getNameFull() {
//        return nameFull;
//    }
//
//    @JsonProperty("name_full")
//    public void setNameFull(String nameFull) {
//        this.nameFull = nameFull;
//    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("coordinates")
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("ancestors")
    public List<Ancestor> getAncestors() {
        return ancestors;
    }

    @JsonProperty("ancestors")
    public void setAncestors(List<Ancestor> ancestors) {
        this.ancestors = ancestors;
    }

    @JsonProperty("descendants")
    public Descendants getDescendants() {
        return descendants;
    }

    @JsonProperty("descendants")
    public void setDescendants(Descendants descendants) {
        this.descendants = descendants;
    }

    @JsonProperty("property_ids")
    public List<String> getPropertyIds() {
        return propertyIds;
    }

    @JsonProperty("property_ids")
    public void setPropertyIds(List<String> propertyIds) {
        this.propertyIds = propertyIds;
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
