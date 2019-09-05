
package com.expedia.young.demo.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The Dimensions Schema
 * <p>
 * An explanation about the purpose of this instance.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "width",
    "height"
})
public class Dimensions {

    /**
     * The Width Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("width")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer width = 0;
    /**
     * The Height Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("height")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer height = 0;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The Width Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    /**
     * The Width Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * The Height Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    /**
     * The Height Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
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
