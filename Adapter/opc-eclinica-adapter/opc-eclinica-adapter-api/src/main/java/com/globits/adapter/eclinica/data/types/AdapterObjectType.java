package com.globits.adapter.eclinica.data.types;

public enum AdapterObjectType {
	LIST_DATA(0),

	SINGLE_DATA(1);

	private final int number;

	private AdapterObjectType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
