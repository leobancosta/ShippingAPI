package com.mynt.exam.constants;

public enum VoucherStatus {
	VOUCHER_CREATED("VOUCHER_CREATED"),
	VOUCHER_DISTRIBUTED("VOUCHER_DISTRIBUTED"),
	VOUCHER_VALIDATED("VOUCHER_VALIDATED"),
	VOUCHER_ACTIVE("VOUCHER_ACTIVE"),
	VOUCHER_REDEEMED("VOUCHER_REDEEMED"),
	VOUCHER_EXPIRED("VOUCHER_EXPIRED"),
	VOUCHER_INVALID("VOUCHER_INVALID");
	
	private final String name;
	
	private VoucherStatus(String s) {
		this.name = s;
	}

	public String getName() {
		return name;
	}

	
}
