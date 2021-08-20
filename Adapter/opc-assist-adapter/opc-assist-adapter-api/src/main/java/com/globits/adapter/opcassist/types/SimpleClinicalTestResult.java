package com.globits.adapter.opcassist.types;

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
