
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
    "request_currency",
    "billable_currency"
})
public class Inclusive {

    @JsonProperty("request_currency")
    private RequestCurrency_ requestCurrency;
    @JsonProperty("billable_currency")
    private BillableCurrency_ billableCurrency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("request_currency")
    public RequestCurrency_ getRequestCurrency() {
        return requestCurrency;
    }

    @JsonProperty("request_currency")
    public void setRequestCurrency(RequestCurrency_ requestCurrency) {
        this.requestCurrency = requestCurrency;
    }

    @JsonProperty("billable_currency")
    public BillableCurrency_ getBillableCurrency() {
        return billableCurrency;
    }

    @JsonProperty("billable_currency")
    public void setBillableCurrency(BillableCurrency_ billableCurrency) {
        this.billableCurrency = billableCurrency;
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
