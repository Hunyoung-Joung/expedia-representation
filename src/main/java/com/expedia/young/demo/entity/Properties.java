
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


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"property_id",
	"name",
	"phone",
	"fax",
	"rank",
	"images",
	"onsite_payments",
	"isPossible",
	"multi_unit",
})

public class Properties {

    @JsonProperty("property_id")
    private String property_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("rank")
    private String rank;
    @JsonProperty("multi_unit")
    private String multi_unit;
    @JsonProperty("onsite_payments")
    private OnsitePayments onsite_payments;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("isPossible")
    private boolean isPossible;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("property_id")
	public String getProperty_id() {
		return property_id;
	}
    @JsonProperty("property_id")
	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}
    @JsonProperty("name")
	public String getName() {
		return name;
	}
    @JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
    @JsonProperty("phone")
	public String getPhone() {
		return phone;
	}
    @JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}
    @JsonProperty("fax")
	public String getFax() {
		return fax;
	}
    @JsonProperty("fax")
	public void setFax(String fax) {
		this.fax = fax;
	}
    @JsonProperty("rank")
	public String getRank() {
		return rank;
	}
    @JsonProperty("rank")
	public void setRank(String rank) {
		this.rank = rank;
	}
    @JsonProperty("multi_unit")
	public String getMulti_unit() {
		return multi_unit;
	}
    @JsonProperty("multi_unit")
	public void setMulti_unit(String multi_unit) {
		this.multi_unit = multi_unit;
	}
    @JsonProperty("onsite_payments")
    public OnsitePayments getOnsite_payments() {
		return onsite_payments;
	}
    @JsonProperty("onsite_payments")
	public void setOnsite_payments(OnsitePayments onsite_payments) {
		this.onsite_payments = onsite_payments;
	}
    @JsonProperty("images")
	public Images getImages() {
		return images;
	}
    @JsonProperty("images")
	public void setImages(Images images) {
		this.images = images;
	}
    @JsonProperty("isPossible")
	public boolean isPossible() {
		return isPossible;
	}
    @JsonAnySetter
	public void setPossible(boolean isPossible) {
		this.isPossible = isPossible;
	}
    @JsonAnySetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    

}
