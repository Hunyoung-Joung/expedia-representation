
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
    "additional_rates",
    "recommendations"
})
public class Links__ {

    @JsonProperty("additional_rates")
    private AdditionalRates additionalRates;
    @JsonProperty("recommendations")
    private Recommendations recommendations;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("additional_rates")
    public AdditionalRates getAdditionalRates() {
        return additionalRates;
    }

    @JsonProperty("additional_rates")
    public void setAdditionalRates(AdditionalRates additionalRates) {
        this.additionalRates = additionalRates;
    }

    @JsonProperty("recommendations")
    public Recommendations getRecommendations() {
        return recommendations;
    }

    @JsonProperty("recommendations")
    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
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
