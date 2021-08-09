package com.mynt.exam.web.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

public class ShippingDetailsDTO implements Serializable {

	private static final long serialVersionUID = -6253822039002983165L;

	@DecimalMin(value="0.01", message="{validation.invalid.weight}")
	private float weight;
	
	@DecimalMin(value="0.01", message="{validation.invalid.height}")
	private float height;
	
	@DecimalMin(value="0.01", message="{validation.invalid.width}")
	private float width;
	
	@DecimalMin(value="0.01", message="{validation.invalid.length}")
	private float length;
		
	private String voucher;

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

}
