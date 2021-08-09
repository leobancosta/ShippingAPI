package com.mynt.exam.web.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ShippingCostDTO implements Serializable {

	private static final long serialVersionUID = 3210284869029041675L;

	private float shippingFee;

	private float discount;
	
	private String shippingStatus;
	
	private String discountStatus;
	
	private String ruleName;

	public float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getDiscountStatus() {
		return discountStatus;
	}

	public void setDiscountStatus(String discountStatus) {
		this.discountStatus = discountStatus;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}	
	
}
