
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
    "exclusive",
    "inclusive"
})
public class Totals {

    @JsonProperty("exclusive")
    private Exclusive exclusive;
    @JsonProperty("inclusive")
    private Inclusive inclusive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("exclusive")
    public Exclusive getExclusive() {
        return exclusive;
    }

    @JsonProperty("exclusive")
    public void setExclusive(Exclusive exclusive) {
        this.exclusive = exclusive;
    }

    @JsonProperty("inclusive")
    public Inclusive getInclusive() {
        return inclusive;
    }

    @JsonProperty("inclusive")
    public void setInclusive(Inclusive inclusive) {
        this.inclusive = inclusive;
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
