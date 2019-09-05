
package com.expedia.young.demo.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The Root Schema
 * <p>
 * An explanation about the purpose of this instance.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "checked",
    "dimensions",
    "id",
    "name",
    "price",
    "tags"
})
public class Properties {

    /**
     * The Checked Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("checked")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Boolean checked = false;
    /**
     * The Dimensions Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("dimensions")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Dimensions dimensions;
    /**
     * The Id Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer id = 0;
    /**
     * The Name Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String name = "";
    /**
     * The Price Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("price")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Double price = 0.0D;
    /**
     * The Tags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("tags")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private List<String> tags = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The Checked Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("checked")
    public Boolean getChecked() {
        return checked;
    }

    /**
     * The Checked Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("checked")
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * The Dimensions Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("dimensions")
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * The Dimensions Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("dimensions")
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * The Id Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * The Id Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The Name Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The Name Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The Price Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    /**
     * The Price Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * The Tags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * The Tags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
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
