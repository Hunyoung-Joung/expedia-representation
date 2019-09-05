
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
    "amenities_code"
})
public class Amenities {

//    @JsonProperty("amenities_code")
//    private AmenitiesCode amenitiesCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    @JsonProperty("amenities_code")
//    public AmenitiesCode getAmenitiesCode() {
//        return amenitiesCode;
//    }
//
//    @JsonProperty("amenities_code")
//    public void setAmenitiesCode(AmenitiesCode amenitiesCode) {
//        this.amenitiesCode = amenitiesCode;
//    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
