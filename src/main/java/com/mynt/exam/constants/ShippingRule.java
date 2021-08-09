package com.mynt.exam.constants;

public enum ShippingRule {
	REJECTED_PARCEL("PARCEL_REJECTED"),
	HEAVY_PARCEL("HEAVY_PARCEL"),
	SMALL_PARCEL("SMALL_PARCEL"),
	MEDIUM_PARCEL("MEDIUM_PARCEL"),
	LARGE_PARCEL("LARGE_PARCEL");
	
	private final String name;
	
	private ShippingRule(String s) {
		this.name = s;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.name;
	}
	
}
		