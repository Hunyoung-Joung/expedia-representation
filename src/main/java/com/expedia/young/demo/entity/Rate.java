
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
    "available_rooms",
    "refundable",
    "fenced_deal",
    "fenced_deal_available",
    "deposit_required",
    "merchant_of_record",
    "amenities",
    "links",
    "bed_groups",
    "cancel_penalties",
    "occupancy_pricing"
})
public class Rate {

    @JsonProperty("id")
    private String id;
    @JsonProperty("available_rooms")
    private String availableRooms;
    @JsonProperty("refundable")
    private String refundable;
    @JsonProperty("fenced_deal")
    private String fencedDeal;
    @JsonProperty("fenced_deal_available")
    private String fencedDealAvailable;
    @JsonProperty("deposit_required")
    private String depositRequired;
    @JsonProperty("merchant_of_record")
    private String merchantOfRecord;
    @JsonProperty("amenities")
    private Amenities amenities;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("bed_groups")
    private BedGroups bedGroups;
    @JsonProperty("cancel_penalties")
    private List<CancelPenalty> cancelPenalties = null;
    @JsonProperty("occupancy_pricing")
    private OccupancyPricing occupancyPricing;
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

    @JsonProperty("available_rooms")
    public String getAvailableRooms() {
        return availableRooms;
    }

    @JsonProperty("available_rooms")
    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }

    @JsonProperty("refundable")
    public String getRefundable() {
        return refundable;
    }

    @JsonProperty("refundable")
    public void setRefundable(String refundable) {
        this.refundable = refundable;
    }

    @JsonProperty("fenced_deal")
    public String getFencedDeal() {
        return fencedDeal;
    }

    @JsonProperty("fenced_deal")
    public void setFencedDeal(String fencedDeal) {
        this.fencedDeal = fencedDeal;
    }

    @JsonProperty("fenced_deal_available")
    public String getFencedDealAvailable() {
        return fencedDealAvailable;
    }

    @JsonProperty("fenced_deal_available")
    public void setFencedDealAvailable(String fencedDealAvailable) {
        this.fencedDealAvailable = fencedDealAvailable;
    }

    @JsonProperty("deposit_required")
    public String getDepositRequired() {
        return depositRequired;
    }

    @JsonProperty("deposit_required")
    public void setDepositRequired(String depositRequired) {
        this.depositRequired = depositRequired;
    }

    @JsonProperty("merchant_of_record")
    public String getMerchantOfRecord() {
        return merchantOfRecord;
    }

    @JsonProperty("merchant_of_record")
    public void setMerchantOfRecord(String merchantOfRecord) {
        this.merchantOfRecord = merchantOfRecord;
    }

    @JsonProperty("amenities")
    public Amenities getAmenities() {
        return amenities;
    }

    @JsonProperty("amenities")
    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("bed_groups")
    public BedGroups getBedGroups() {
        return bedGroups;
    }

    @JsonProperty("bed_groups")
    public void setBedGroups(BedGroups bedGroups) {
        this.bedGroups = bedGroups;
    }

    @JsonProperty("cancel_penalties")
    public List<CancelPenalty> getCancelPenalties() {
        return cancelPenalties;
    }

    @JsonProperty("cancel_penalties")
    public void setCancelPenalties(List<CancelPenalty> cancelPenalties) {
        this.cancelPenalties = cancelPenalties;
    }

    @JsonProperty("occupancy_pricing")
    public OccupancyPricing getOccupancyPricing() {
        return occupancyPricing;
    }

    @JsonProperty("occupancy_pricing")
    public void setOccupancyPricing(OccupancyPricing occupancyPricing) {
        this.occupancyPricing = occupancyPricing;
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
