
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
    "occupancy_pricing_code"
})
public class OccupancyPricing {

    @JsonProperty("occupancy_pricing_code")
    private OccupancyPricingCode occupancyPricingCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("occupancy_pricing_code")
    public OccupancyPricingCode getOccupancyPricingCode() {
        return occupancyPricingCode;
    }

    @JsonProperty("occupancy_pricing_code")
    public void setOccupancyPricingCode(OccupancyPricingCode occupancyPricingCode) {
        this.occupancyPricingCode = occupancyPricingCode;
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
