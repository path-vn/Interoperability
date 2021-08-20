package com.globits.adapter.pdma.types;

public enum AddressType {
	PLACE_OF_BIRTH(0),

	RESIDENT_ADDRESS(1),

	CURRENT_ADDRESS(2),

	SHIPPING_ADDRESS(3),

	BILLING_ADDRESS(4),

	HOME_ADDRESS(5),

	OFFICE_ADDRESS(6),

	OTHER(7);

	private final int number;

	private AddressType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
