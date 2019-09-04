package com.expedia.young.demo.entity;

public final class ConditionInfo {

	private String checkin;
	private String checkout;
	private String region_id;
	private String occupancy;

    public String getCheckin() {
		return checkin;
	}



	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}



	public String getCheckout() {
		return checkout;
	}



	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}



	public String getRegion_id() {
		return region_id;
	}



	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}



	public String getOccupancy() {
		return occupancy;
	}



	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}



	@Override
    public String toString() {
        return "PersonalInfo [checkin="+checkin+", checkout="+checkout+", region_id="+region_id+", occupancy="+occupancy+"]";
    }
}
