package com.globits.adapter.pdma.types;

public enum SimpleClinicalTestResult {
	NEGATIVE(0),

	POSITIVE(1),

	UNDETERMINED(2);

	private final int number;

	private SimpleClinicalTestResult(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
